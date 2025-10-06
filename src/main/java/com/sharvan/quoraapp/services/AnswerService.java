package com.sharvan.quoraapp.services;


import com.sharvan.quoraapp.dto.AnswerRequestDTO;
import com.sharvan.quoraapp.dto.AnswerResponseDTO;
import com.sharvan.quoraapp.models.Answer;
import com.sharvan.quoraapp.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {
        Answer answer = Answer.builder()
                .content(answerRequestDTO.getContent())
                .questionId(answerRequestDTO.getQuestionId())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return answerRepository.save(answer)
                .map(this::toResponseDTO);
    }

    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String answerId) {
        return answerRepository.findById(answerId)
                .map(this::toResponseDTO);
    }

    @Override
    public Flux<AnswerResponseDTO> listAnswersByQuestionId(String questionId) {
        return answerRepository.findByQuestionIdOrderByCreatedAtAsc(questionId)
                .map(this::toResponseDTO);
    }

    @Override
    public Mono<AnswerResponseDTO> updateAnswer(String answerId, AnswerRequestDTO requestDTO) {
        return answerRepository.findById(answerId)
                .flatMap(existing -> {
                    existing.setContent(requestDTO.getContent());
                    existing.setUpdatedAt(Instant.now());
                    return answerRepository.save(existing);
                })
                .map(this::toResponseDTO);
    }

    @Override
    public Mono<Void> deleteAnswer(String answerId) {
        return answerRepository.deleteById(answerId);
    }

    private AnswerResponseDTO toResponseDTO(Answer answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt() != null ? answer.getCreatedAt().toString() : null)
                .updatedAt(answer.getUpdatedAt() != null ? answer.getUpdatedAt().toString() : null)
                .build();
    }
}


