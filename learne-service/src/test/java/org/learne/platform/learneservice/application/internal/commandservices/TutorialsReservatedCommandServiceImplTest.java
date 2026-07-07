package org.learne.platform.learneservice.application.internal.commandservices;

import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.TutorialsReservatedRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TutorialsReservatedCommandServiceImplTest {

    private final TutorialsReservatedRepository repository = mock(TutorialsReservatedRepository.class);
    private final TutorialsReservatedCommandServiceImpl service = new TutorialsReservatedCommandServiceImpl(repository);

    @Test
    void handle_shouldCreateTutorialsReservatedSuccessfully() {
        CreateTutorialsReservatedCommand command = new CreateTutorialsReservatedCommand(1L, 1L);
        TutorialsReservated expected = new TutorialsReservated(command);
        expected.setId(1L);

        when(repository.existsByStudentIdAndTutorialsCoursesId(1L, 1L)).thenReturn(false);
        when(repository.save(any())).thenReturn(expected);

        Optional<TutorialsReservated> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void handle_shouldThrowExceptionWhenDuplicate() {
        CreateTutorialsReservatedCommand command = new CreateTutorialsReservatedCommand(1L, 1L);
        when(repository.existsByStudentIdAndTutorialsCoursesId(1L, 1L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.handle(command));
    }
}