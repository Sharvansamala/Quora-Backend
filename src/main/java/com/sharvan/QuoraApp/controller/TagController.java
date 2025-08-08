package com.sharvan.QuoraApp.controller;

import com.sharvan.QuoraApp.dto.TagRequest;
import com.sharvan.QuoraApp.dto.TagResponse;
import com.sharvan.QuoraApp.services.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final ITagService tagService;

    @PostMapping("/create")
    public Mono<TagResponse> createTag(@RequestBody TagRequest tagRequest) {
        return tagService.createTag(tagRequest)
                .doOnSuccess(tagResponse -> System.out.println("Tag created successfully: " + tagResponse))
                .doOnError(error -> System.err.println("Error creating tag: " + error));
    }

    @GetMapping()
    public Flux<TagResponse> getQuestionsByTag(@RequestParam String tagName) {
        return tagService.getQuestionsByTag(tagName)
                .doOnNext(questionResponse -> System.out.println("Question retrieved for tag: " + questionResponse))
                .doOnError(error -> System.err.println("Error retrieving questions for tag: " + error));
    }
}
