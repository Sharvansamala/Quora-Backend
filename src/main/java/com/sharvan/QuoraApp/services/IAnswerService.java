package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.dto.AnswerRequestDTO;
import com.sharvan.QuoraApp.dto.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String answerId);

}
