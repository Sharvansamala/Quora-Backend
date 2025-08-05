package com.sharvan.QuoraApp.controller;

import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}


