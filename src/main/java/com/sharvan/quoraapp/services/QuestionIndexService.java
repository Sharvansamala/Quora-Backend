package com.sharvan.quoraapp.services;

import org.springframework.stereotype.Service;

import com.sharvan.quoraapp.models.Question;
import com.sharvan.quoraapp.models.QuestionElasticDocument;
import com.sharvan.quoraapp.repositories.QuestionDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Question question) {
        QuestionElasticDocument document = QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();
        questionDocumentRepository.save(document);
    }

}
