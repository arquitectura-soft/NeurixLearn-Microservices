package org.learne.platform.learneservice.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.learne.platform.learneservice.domain.model.commands.Unit.CreateUnitCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@Setter
public class Unit extends AuditableAbstractAggregateRoot<Unit> {

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @Column(nullable = false)
    private String title;

    public Unit() {}

    public Unit(CreateUnitCommand command) {
        this.course = new Course(command.courseId());
        this.title = command.title();
    }

    public Unit(Long id) {
        this.setId(id);
    }
}
