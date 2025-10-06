package com.sharvan.quoraapp.repositories;

import com.sharvan.quoraapp.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    Flux<Answer> findByQuestionIdOrderByCreatedAtAsc(String questionId);
}
