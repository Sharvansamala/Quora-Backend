package com.sharvan.QuoraApp.controller;

import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponse> createQuestion(@RequestBody QuestionRequest questionRequest) {
        return questionService.createQuestion(questionRequest)
                .doOnSuccess(questionResponse -> System.out.println("Question created successfully: " + questionResponse))
                .doOnError(error -> System.err.println("Error creating question: " + error));
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponse> getQuestionById(@PathVariable String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping()
    public Flux<QuestionResponse> getAllQuestions() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestion(@PathVariable String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/search")
    public Flux<QuestionResponse> searchQuestions(@RequestParam String query,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size
    ) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }


    @GetMapping("/tag/{tag}")
    public Flux<QuestionResponse> getQuestionsByTag(@PathVariable String tag,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }


}


