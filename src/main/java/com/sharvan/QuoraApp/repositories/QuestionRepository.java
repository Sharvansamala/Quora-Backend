package com.sharvan.QuoraApp.repositories;

import com.sharvan.QuoraApp.models.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

//   Flux<Question> findByAuthorId(String authorId);

//   Mono<Long> countByAuthorId(String authorId);

   @Query("{ $or : [ { 'title': { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } } ] }")
   Flux<Question> findByTitleOrContentContainingIgnoreCase(String query, Pageable pageable);

   Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursorDateTime, Pageable pageable);

   Flux<Question> findTop10ByOrderByCreatedAtAsc();
}
