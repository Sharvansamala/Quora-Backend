package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.models.Question;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest);
}
