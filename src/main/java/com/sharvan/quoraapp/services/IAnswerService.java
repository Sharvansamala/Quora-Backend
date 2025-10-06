package com.sharvan.quoraapp.services;

import com.sharvan.quoraapp.dto.AnswerRequestDTO;
import com.sharvan.quoraapp.dto.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String answerId);

}
