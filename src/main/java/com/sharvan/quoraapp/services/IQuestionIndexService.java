package com.sharvan.quoraapp.services;

import org.springframework.stereotype.Service;

import com.sharvan.quoraapp.models.Question;


@Service
public interface IQuestionIndexService {

    void createQuestionIndex(Question question);
}
