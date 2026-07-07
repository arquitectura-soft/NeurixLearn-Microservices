package org.learne.platform.learneservice.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CourseRepository;
import org.learne.platform.learneservice.interfaces.rest.resources.Material.CreateMaterialResource;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long courseId;

    @BeforeEach
    void setUp() {
        User teacher = new User();
        teacher.setFirstName("Jane");
        teacher.setLastName("Smith");
        teacher.setUsername("jsmith");
        teacher.setEmail("jsmith@example.com");
        teacher.setPassword("123456");
        teacher.setType_user(1);
        teacher.setType_plan(1);
        teacher = userRepository.save(teacher);

        Course course = new Course();
        course.setTitle("Curso REST");
        course.setDescription("REST test");
        course.setLevel("Intermedio");
        course.setDuration("3h");
        course.setPrior_knowledge("Java básico");
        course.setPrincipal_image("img.jpg");
        course.setUrl_video("http://test.video");
        course.setTeacherId(1L);
        course = courseRepository.save(course);

        courseId = course.getId();
    }

    @Test
    void createMaterial_shouldReturn201AndMaterialResource() throws Exception {
        CreateMaterialResource resource = new CreateMaterialResource(
                courseId, "Material desde test", "PDF", "http://testlink.com"
        );

        mockMvc.perform(post("/api/v1/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Material desde test"))
                .andExpect(jsonPath("$.format").value("PDF"))
                .andExpect(jsonPath("$.link").value("http://testlink.com"));
    }
}