package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest);

    Mono<QuestionResponse> getQuestionById(String id);

    Flux<QuestionResponse> getAllQuestions(String cursor, int size);

    Mono<Void> deleteQuestionById(String id);

    Flux<QuestionResponse> searchQuestion(String query, int page, int size);
}
