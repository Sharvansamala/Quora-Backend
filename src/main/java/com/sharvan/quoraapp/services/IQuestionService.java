package com.sharvan.quoraapp.services;

import java.util.List;

import com.sharvan.quoraapp.dto.QuestionRequest;
import com.sharvan.quoraapp.dto.QuestionResponse;
import com.sharvan.quoraapp.models.QuestionElasticDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest);

    Mono<QuestionResponse> getQuestionById(String id);

    Flux<QuestionResponse> getAllQuestions(String cursor, int size);

    Mono<Void> deleteQuestionById(String id);

    Flux<QuestionResponse> searchQuestion(String query, int page, int size);

    List<QuestionElasticDocument> searchQuestionsByElasticsearch(String query);
}
