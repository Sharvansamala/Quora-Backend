package com.sharvan.quoraapp.repositories;

import com.sharvan.quoraapp.models.Tag;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface TagRepository extends ReactiveMongoRepository<Tag, String> {
    Mono<Tag> findByName(String tagName);
    @Query("{ 'name': ?0 }")
    Flux<Tag> findAllByTagName(String tagName);
    // Additional query methods can be defined here if needed
}
