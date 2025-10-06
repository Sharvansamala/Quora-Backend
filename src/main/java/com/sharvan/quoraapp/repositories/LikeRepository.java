package com.sharvan.quoraapp.repositories;

import com.sharvan.quoraapp.models.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends ReactiveMongoRepository<Like,String> {
}
