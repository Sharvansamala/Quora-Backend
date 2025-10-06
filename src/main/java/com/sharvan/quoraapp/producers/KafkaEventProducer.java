package com.sharvan.quoraapp.producers;

import com.sharvan.quoraapp.config.KafkaConfig;
import com.sharvan.quoraapp.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent){
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME,viewCountEvent.getTargetId(),viewCountEvent)
                .whenComplete((result,ex)->{
                    if(ex==null){
                        System.out.println("Event published successfully to topic "+result.getRecordMetadata().topic()
                                +" partition "+result.getRecordMetadata().partition()
                                +" offset "+result.getRecordMetadata().offset());
                    }else{
                        System.out.println("Error publishing event "+ex.getMessage());
                    }
                });

    }
}
