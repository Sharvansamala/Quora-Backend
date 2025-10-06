package com.sharvan.quoraapp.services;

import com.sharvan.quoraapp.adapter.QuestionAdapter;
import com.sharvan.quoraapp.adapter.TagAdapter;
import com.sharvan.quoraapp.dto.QuestionRequest;
import com.sharvan.quoraapp.dto.QuestionResponse;
import com.sharvan.quoraapp.events.ViewCountEvent;
import com.sharvan.quoraapp.models.Question;
import com.sharvan.quoraapp.models.Tag;
import com.sharvan.quoraapp.producers.KafkaEventProducer;
import com.sharvan.quoraapp.repositories.QuestionRepository;
import com.sharvan.quoraapp.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final ITagService tagService;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest) {

        List<Tag> tags = questionRequest.getTags().stream()
                .map(TagAdapter::toTag)
                .toList();

        Question question = Question.builder()
                .title(questionRequest.getTitle())
                .content(questionRequest.getContent())
                .tag(tags)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        for (Tag tag : tags) {
            tagService.AddQuestionToTag(tag.getName(), question);
        }

        return questionRepository.save(question)
                .map(savedQuestion -> QuestionAdapter.toQuestionResponse(savedQuestion))
                .doOnSuccess(questionResponse -> System.out.println("Question created successfully: " + questionResponse))
                .doOnError(error -> System.err.println("Error creating question: " + error));
    }

    @Override
    public Mono<QuestionResponse> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponse)
                .doOnSuccess(success -> {
                            System.out.println("Question retrieved successfully");
                            ViewCountEvent viewCountEvent = new ViewCountEvent(id, "Question", LocalDateTime.now());
                            kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                        }
                )
                .doOnError(error -> System.err.println("Error retrieving question: " + error));
    }

    @Override
    public Flux<QuestionResponse> getAllQuestions(String cursor, int size) {

        Pageable pageable = PageRequest.of(0,size);

        if (CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toQuestionResponse)
                    .doOnComplete(() -> System.out.println("Top questions retrieved successfully"))
                    .doOnError(error -> System.err.println("Error retrieving top questions: " + error));
        }else{
            LocalDateTime cursorDateTime = CursorUtils.parseCursor(cursor);
           return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorDateTime,pageable)
                   .map(QuestionAdapter::toQuestionResponse)
                   .doOnComplete(() -> System.out.println("Questions retrieved successfully"))
                     .doOnError(error -> System.err.println("Error retrieving questions: " + error));
        }

    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return questionRepository.deleteById(id)
                .doOnSuccess(success -> System.out.println("Success " + success))
                .doOnError(error -> System.out.println("Error " + error));
    }

    @Override
    public Flux<QuestionResponse> searchQuestion(String query, int page, int size) {
        return questionRepository.findByTitleOrContentContainingIgnoreCase(query, PageRequest.of(page,size))
                .map(QuestionAdapter::toQuestionResponse)
                .doOnComplete(()-> System.out.println("Success"))
                .doOnError(error-> System.out.println("Error "+error));
    }
}
