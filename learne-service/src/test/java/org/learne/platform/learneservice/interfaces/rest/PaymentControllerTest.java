package org.learne.platform.learneservice.interfaces.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.PaymentRepository;
import org.learne.platform.learneservice.interfaces.rest.resources.Payment.CreatePaymentResource;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private Long studentId;

    @BeforeEach
    void setup() {
        // Configura el mock para asignar IDs al guardar usuarios
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(System.currentTimeMillis()); // Simula un ID único
            return u;
        });

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Student");
        user.setUsername("student_" + System.currentTimeMillis()); // para evitar duplicados
        user.setEmail("student_" + System.currentTimeMillis() + "@example.com");
        user.setPassword("password");
        user.setType_user(1);
        user.setType_plan(1);
        studentId = userRepository.save(user).getId();
    }

    @Test
    void createPayment_ShouldReturnCreated() {
        // Arrange
        CreatePaymentResource resource = new CreatePaymentResource(
                "Jane Card", 1234567812345678L, "10/30", 123, "jane@card.com", studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatePaymentResource> request = new HttpEntity<>(resource, headers);

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/payments", request, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).contains("Jane Card");
    }

    @Test
    void createPayment_ShouldReturnBadRequestIfDuplicate() {
        // Arrange
        CreatePaymentResource resource = new CreatePaymentResource(
                "Jane Card", 1234567812345678L, "10/30", 123, "jane@card.com", studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatePaymentResource> request = new HttpEntity<>(resource, headers);
        restTemplate.postForEntity("http://localhost:" + port + "/api/v1/payments", request, String.class);

        // Act - Try to create again
        ResponseEntity<String> duplicateResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/payments", request, String.class);

        // Assert
        assertThat(duplicateResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}