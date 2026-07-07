package org.learne.platform.learneservice.application.internal.queryservices;

import org.junit.jupiter.api.Test;
import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.domain.model.queries.TutorialsReservated.GetAllTutorialsReservatedQuery;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.TutorialsReservatedRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TutorialsReservatedQueryServiceImplTest {

    private final TutorialsReservatedRepository repository = mock(TutorialsReservatedRepository.class);
    private final TutorialsReservatedQueryServiceImpl service = new TutorialsReservatedQueryServiceImpl(repository);

    @Test
    void handle_shouldReturnAllTutorialsReservated() {
        when(repository.findAll()).thenReturn(List.of(new TutorialsReservated(), new TutorialsReservated()));

        var result = service.handle(new GetAllTutorialsReservatedQuery());

        assertEquals(2, result.size());
    }
}