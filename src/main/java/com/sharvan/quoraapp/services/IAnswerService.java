package com.sharvan.quoraapp.services;

import com.sharvan.quoraapp.dto.AnswerRequestDTO;
import com.sharvan.quoraapp.dto.AnswerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String answerId);

    public Flux<AnswerResponseDTO> listAnswersByQuestionId(String questionId);

    public Mono<AnswerResponseDTO> updateAnswer(String answerId, AnswerRequestDTO requestDTO);

    public Mono<Void> deleteAnswer(String answerId);

}
