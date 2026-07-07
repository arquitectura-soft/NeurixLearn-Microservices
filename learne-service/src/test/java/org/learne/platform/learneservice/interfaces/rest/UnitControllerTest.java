package org.learne.platform.learneservice.interfaces.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CourseRepository;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.UnitRepository;
import org.learne.platform.learneservice.interfaces.rest.resources.Unit.CreateUnitResource;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnitControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Mock
    private UserRepository userRepository;

    private Long courseId;

    @BeforeEach
    void setup() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("123456");
        user.setType_user(1);
        user.setType_plan(1);
        user = userRepository.save(user); // 💡 guardar el user

        Course course = new Course();
        course.setTitle("Course A");
        course.setDescription("desc");
        course.setDuration("1h");
        course.setLevel("Basic");
        course.setPrior_knowledge("None");
        course.setPrincipal_image("img.jpg");
        course.setUrl_video("video.com");
        course.setTeacherId(1L); // Assuming teacherId is 1L
        courseId = courseRepository.save(course).getId();
    }
    @Test
    void testCreateUnit() {
        CreateUnitResource resource = new CreateUnitResource(courseId, "Unit X");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUnitResource> request = new HttpEntity<>(resource, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/units", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void testGetAllUnits() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/units", String.class);

        assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
    }
}