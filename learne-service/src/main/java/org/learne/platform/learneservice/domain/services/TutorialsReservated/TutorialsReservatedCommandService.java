package org.learne.platform.learneservice.domain.services.TutorialsReservated;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.domain.model.commands.CreateTutorialsReservatedCommand;

import java.util.Optional;

public interface TutorialsReservatedCommandService {
    Optional<TutorialsReservated> handle(CreateTutorialsReservatedCommand command);
}
