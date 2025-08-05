package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest);

    Mono<QuestionResponse> getQuestionById(String id);
}
