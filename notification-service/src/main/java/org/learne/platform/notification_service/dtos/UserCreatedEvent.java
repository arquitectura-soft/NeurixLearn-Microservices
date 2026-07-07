package org.learne.platform.notification_service.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class UserCreatedEvent {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer type_user;
    private Integer type_plan;
    private Instant createdAt;
    private Instant updatedAt;
}
