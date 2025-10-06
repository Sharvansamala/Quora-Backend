package com.sharvan.quoraapp.services;


import com.sharvan.quoraapp.dto.TagRequest;
import com.sharvan.quoraapp.dto.TagResponse;
import com.sharvan.quoraapp.models.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITagService {
    Flux<TagResponse> getQuestionsByTag(String tagName);

    Mono<TagResponse> createTag(TagRequest tagRequest);

    void AddQuestionToTag(String tagName, Question question);
}
