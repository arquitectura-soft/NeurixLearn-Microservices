package org.learne.platform.learneservice.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.learne.platform.learneservice.domain.model.commands.CoursesEnrollment.CreateCoursesEnrollmentCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class CoursesEnrollment extends AuditableAbstractAggregateRoot<CoursesEnrollment> {

    @JoinColumn(name = "student_id", nullable = false)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public CoursesEnrollment() {}

    public CoursesEnrollment(CreateCoursesEnrollmentCommand command) {
        this.studentId = command.studentId();
        this.course = new Course(command.courseId());
    }
}
