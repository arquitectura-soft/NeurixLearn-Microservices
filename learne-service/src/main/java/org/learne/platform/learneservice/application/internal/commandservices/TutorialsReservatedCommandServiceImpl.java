package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learneservice.domain.services.TutorialsReservated.TutorialsReservatedCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.TutorialsReservatedRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorialsReservatedCommandServiceImpl implements TutorialsReservatedCommandService {
    private final TutorialsReservatedRepository tutorialsReservatedRepository;
    public TutorialsReservatedCommandServiceImpl(TutorialsReservatedRepository tutorialsReservatedRepository) {
        this.tutorialsReservatedRepository = tutorialsReservatedRepository;
    }

    @Override
    public Optional<TutorialsReservated> handle(CreateTutorialsReservatedCommand command) {
        if (tutorialsReservatedRepository.existsByStudentIdAndTutorialsCoursesId(command.studentId(), command.tutorialId())) {
            throw new IllegalArgumentException("Student Id and Tutorial Id already exists");
        }
        var tutorialsReservated = new TutorialsReservated(command);
        var createTutorialsReservated = tutorialsReservatedRepository.save(tutorialsReservated);
        return Optional.of(createTutorialsReservated);
    }
}
