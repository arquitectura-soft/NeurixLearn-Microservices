package org.learne.platform.learneservice.interfaces.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.domain.model.aggregates.*;
import org.learne.platform.learneservice.domain.services.notes.NotesCommandService;
import org.learne.platform.learneservice.domain.services.notes.NotesQueryServices;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.*;
import org.learne.platform.learneservice.interfaces.rest.resources.Notes.CreateNotesResource;
import org.learne.platform.learneservice.interfaces.rest.resources.Notes.NoteResource;
import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotesControllerTest {

    @Autowired
    private NotesController notesController;

    @Autowired
    private NotesCommandService notesCommandService;

    @Autowired
    private NotesQueryServices notesQueryServices;

    @Autowired
    private NotesRepository notesRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ExamRepository examRepository;

    private User student;
    private Exam exam;

    @BeforeEach
    void setUp() {
        // Configurar el mock para asignar IDs al guardar usuarios
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            if (u.getType_user() == 1) u.setId(1L); // Profesor
            else u.setId(2L); // Estudiante
            return u;
        });

        // Crear profesor
        User teacher = new User();
        teacher.setFirstName("Juan");
        teacher.setLastName("Pérez");
        teacher.setUsername("jperez");
        teacher.setEmail("jperez@correo.com");
        teacher.setPassword("123456");
        teacher.setType_user(1);
        teacher.setType_plan(1);
        userRepository.save(teacher);

        // Crear estudiante
        student = new User();
        student.setFirstName("Pedro");
        student.setLastName("López");
        student.setUsername("plopez");
        student.setEmail("plopez@correo.com");
        student.setPassword("123456");
        student.setType_user(2);
        student.setType_plan(1);
        student = userRepository.save(student);

        // Crear curso
        Course course = new Course();
        course.setTitle("Curso Test");
        course.setDescription("Curso básico");
        course.setDuration("2 semanas");
        course.setLevel("Básico");
        course.setPrincipal_image("img.jpg");
        course.setPrior_knowledge("Ninguno");
        course.setUrl_video("url");
        course.setTeacherId(teacher.getId());
        course = courseRepository.save(course);

        // Crear unidad
        Unit unit = new Unit();
        unit.setTitle("Unidad 1");
        unit.setCourse(course);
        unit = unitRepository.save(unit);

        // Crear examen
        exam = new Exam();
        exam.setTitle("Examen 1");
        exam.setCourse(course);
        exam.setUnit(unit);
        exam = examRepository.save(exam);

        // Crear nota real
        Notes note = new Notes();
        note.setStudentId(1L); // Asignar ID del estudiante
        note.setExam(exam);
        note.setNote(18.5f);
        notesRepository.save(note);
    }

    @Test
    void createNotes_shouldReturn201() {
        CreateNotesResource resource = new CreateNotesResource(student.getId(), exam.getId(), 19.0f);
        ResponseEntity<NoteResource> response = notesController.createNotes(resource);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNoteById_shouldReturn200() {
        Notes note = notesRepository.findAll().get(0);
        ResponseEntity<NoteResource> response = notesController.getNoteById(note.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getAllNotes_shouldReturn200() {
        ResponseEntity<List<NoteResource>> response = notesController.getAllNotes();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }
}