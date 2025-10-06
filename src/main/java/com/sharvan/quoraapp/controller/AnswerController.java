package com.sharvan.quoraapp.controller;

import com.sharvan.quoraapp.dto.AnswerRequestDTO;
import com.sharvan.quoraapp.dto.AnswerResponseDTO;
import com.sharvan.quoraapp.services.IAnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AnswerResponseDTO> createAnswer(@RequestBody @Valid AnswerRequestDTO request) {
        return answerService.createAnswer(request);
    }

    @GetMapping("/{id}")
    public Mono<AnswerResponseDTO> getAnswer(@PathVariable("id") String id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping
    public Flux<AnswerResponseDTO> listAnswers(@RequestParam("questionId") String questionId) {
        return answerService.listAnswersByQuestionId(questionId);
    }

    @PutMapping("/{id}")
    public Mono<AnswerResponseDTO> updateAnswer(@PathVariable("id") String id,
                                                @RequestBody @Valid AnswerRequestDTO request) {
        return answerService.updateAnswer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAnswer(@PathVariable("id") String id) {
        return answerService.deleteAnswer(id);
    }
}


