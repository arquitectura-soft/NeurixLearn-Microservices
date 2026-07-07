package org.learne.platform.profileservice.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    @Value("${topic.name.user-created}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public UserEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void publishUserCreatedEvent(User user) {
        try {
            String message = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(topicName, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Puedes manejar con logs también
        }
    }
}
