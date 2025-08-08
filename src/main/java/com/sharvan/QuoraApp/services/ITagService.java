package com.sharvan.QuoraApp.services;


import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.dto.TagRequest;
import com.sharvan.QuoraApp.dto.TagResponse;
import com.sharvan.QuoraApp.models.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITagService {
    Flux<TagResponse> getQuestionsByTag(String tagName);

    Mono<TagResponse> createTag(TagRequest tagRequest);

    void AddQuestionToTag(String tagName, Question question);
}
