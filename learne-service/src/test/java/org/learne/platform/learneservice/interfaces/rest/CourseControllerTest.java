package org.learne.platform.learneservice.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.interfaces.rest.resources.Course.CreateCourseResource;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    private Long teacherId;
    private CreateCourseResource createCourseResource;

    @BeforeEach
    void setup() {
        // Configura el mock para asignar un ID al usuario guardado
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        User teacher = new User();
        teacher.updateUser(
                "Test", "Teacher", "teacheruser",
                "teacher@example.com", "password123",
                1, 1
        );
        teacher = userRepository.save(teacher);
        teacherId = teacher.getId();

        createCourseResource = new CreateCourseResource(
                "Test Title", "Test Description", teacherId,
                "Beginner", "2h", "Basics", "img.jpg", "video.mp4"
        );
    }

    @Test
    @Rollback
    void createCourse_shouldReturnCreated() throws Exception {
        mockMvc.perform(post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCourseResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.teacher_id").value(teacherId))
                .andExpect(jsonPath("$.level").value("Beginner"))
                .andExpect(jsonPath("$.duration").value("2h"));
    }

    @Test
    @Rollback
    void getAllCourses_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Rollback
    void getCourseById_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/courses/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    void createAndGetCourseById_shouldReturnCourse() throws Exception {
        String json = objectMapper.writeValueAsString(createCourseResource);
        String response = mockMvc.perform(post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long createdCourseId = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/api/v1/courses/" + createdCourseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.teacher_id").value(teacherId));
    }
}