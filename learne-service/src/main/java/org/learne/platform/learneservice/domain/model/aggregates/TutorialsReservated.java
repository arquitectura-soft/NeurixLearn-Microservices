package org.learne.platform.learneservice.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.learne.platform.learneservice.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@Setter
public class TutorialsReservated extends AuditableAbstractAggregateRoot<TutorialsReservated> {
    @JoinColumn(name = "student_id", nullable = false)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "tutorial_id", nullable = false)
    private TutorialsCourses tutorialsCourses;

    public TutorialsReservated() {}

    public TutorialsReservated(CreateTutorialsReservatedCommand command) {
        this.studentId = command.studentId();
        this.tutorialsCourses = new TutorialsCourses(command.tutorialId());
    }
}
