package com.sharvan.quoraapp.consumers;

import com.sharvan.quoraapp.config.KafkaConfig;
import com.sharvan.quoraapp.events.ViewCountEvent;
import com.sharvan.quoraapp.models.Question;
import com.sharvan.quoraapp.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(
            topics = KafkaConfig.TOPIC_NAME,
            groupId = "view-count-consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeQuestionViewedEvent(ViewCountEvent viewCountEvent) {
        // Assuming questionId is passed as a String
        Mono<Question> questionOpt = questionRepository.findById(viewCountEvent.getTargetId());
        questionOpt.flatMap(question -> {
            Integer views = question.getViews();
            question.setViews(views==null ? 1 : views + 1);
            return questionRepository.save(question);
        }).subscribe(updatedQuestion -> {
            System.out.println("Updated view count for question ID " + updatedQuestion.getId() + ": " + updatedQuestion.getViews());
        }, error -> {
            System.err.println("Error updating view count for question ID " + viewCountEvent.getTargetId() + ": " + error.getMessage());
        });
    }
}
