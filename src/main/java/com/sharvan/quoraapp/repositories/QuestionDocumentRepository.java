package com.sharvan.quoraapp.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sharvan.quoraapp.models.QuestionElasticDocument;

@Repository
public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument, String> {

    List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String content);
}
