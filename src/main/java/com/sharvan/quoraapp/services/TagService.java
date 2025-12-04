package com.sharvan.quoraapp.services;

import com.sharvan.quoraapp.adapter.TagAdapter;
import com.sharvan.quoraapp.dto.TagRequest;
import com.sharvan.quoraapp.dto.TagResponse;
import com.sharvan.quoraapp.models.Question;
import com.sharvan.quoraapp.models.Tag;
import com.sharvan.quoraapp.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService implements ITagService{

    private final TagRepository tagRepository;


    @Override
    public Flux<TagResponse> getQuestionsByTag(String tagName) {
            return tagRepository.findAllByTagName(tagName)
                    .map(TagAdapter::toTagResponse)
                    .doOnComplete(() -> log.info("Question retrieval for tag completed successfully"))
                    .doOnError(error -> log.error("Error retrieving questions for tag: " + error));
    }

    @Override
    public Mono<TagResponse> createTag(TagRequest tagRequest) {
        Tag tag = Tag.builder()
                .name(tagRequest.getName())
                .build();
        return tagRepository.save(tag)
                .map(TagAdapter::toTagResponse)
                .doOnSuccess(tagResponse -> System.out.println("Tag created successfully: " + tagResponse))
                .doOnError(error -> System.err.println("Error creating tag: " + error));
    }

    @Override
    public void AddQuestionToTag(String tagName, Question question) {
        Mono<Tag> tag = tagRepository.findByName(tagName);
        if(tag != null) {
            tag.flatMap(existingTag -> {
                existingTag.getQuestion().add(question);
                return tagRepository.save(existingTag);
            })
                    .flatMap(savedTag -> Mono.just(savedTag))
                    .doOnSuccess(savedTag -> System.out.println("Question added to existing tag successfully: " + savedTag))
                    .doOnError(error -> System.err.println("Error adding question to existing tag: " + error));
        }else {
            Tag newTag = Tag.builder()
                    .name(tagName)
                    .build();
            tagRepository.save(newTag)
                    .doOnSuccess(savedTag -> {
                        savedTag.getQuestion().add(question);
                        tagRepository.save(savedTag)
                                .doOnSuccess(updatedTag -> System.out.println("Question added to new tag successfully: " + updatedTag))
                                .doOnError(error -> System.err.println("Error adding question to new tag: " + error));
                    });
        }
    }
}
