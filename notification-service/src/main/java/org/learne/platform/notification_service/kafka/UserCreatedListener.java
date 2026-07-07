package org.learne.platform.notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.learne.platform.notification_service.dtos.UserCreatedEvent;
import org.learne.platform.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedListener {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public UserCreatedListener(EmailService emailService) {
        this.emailService = emailService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Soporte para Instant
    }

    @KafkaListener(topics = "${topic.name.user-created}", groupId = "notification-service")
    public void handleUserCreated(String message) {
        try {
            UserCreatedEvent event = objectMapper.readValue(message, UserCreatedEvent.class);

            // Imprimir mensaje en consola
            System.out.println("✅ New user created: " + event.getUsername() + " (" + event.getEmail() + ")");

            // Enviar correo
            String subject = "¡Bienvenido a NeurixLearn, " + event.getFirstName() + "!";
            String body = "Hola " + event.getFirstName() + " " + event.getLastName() + ",\n\n"
                    + "Tu cuenta con el usuario \"" + event.getUsername() + "\" ha sido creada exitosamente.\n"
                    + "Tipo de usuario: " + (event.getType_user() == 1 ? "Estudiante" : "Profesor") + "\n"
                    + "Plan: " + (event.getType_plan() == 1 ? "Gratis" : "Premium") + "\n\n"
                    + "¡Bienvenido a bordo!\n"
                    + "NeurixLearn Platform Team";

            emailService.sendUserCreatedEmail(event.getEmail(), subject, body);

        } catch (Exception e) {
            System.err.println("❌ Failed to process user-created event: " + e.getMessage());
        }
    }
}
