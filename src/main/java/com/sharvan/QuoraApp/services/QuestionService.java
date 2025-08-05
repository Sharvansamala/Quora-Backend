package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.adapter.QuestionAdapter;
import com.sharvan.QuoraApp.dto.QuestionRequest;
import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.models.Question;
import com.sharvan.QuoraApp.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponse> createQuestion(QuestionRequest questionRequest) {

        Question question = Question.builder()
                .title(questionRequest.getTitle())
                .content(questionRequest.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return questionRepository.save(question)
                .map(savedQuestion -> QuestionAdapter.toQuestionResponse(savedQuestion))
                .doOnSuccess(questionResponse -> System.out.println("Question created successfully: " + questionResponse))
                .doOnError(error -> System.err.println("Error creating question: " + error));
    }

    @Override
    public Mono<QuestionResponse> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponse)
                .doOnSuccess(success-> System.out.println("Question retrieved successfully"))
                .doOnError(error -> System.err.println("Error retrieving question: " + error));
    }
}
