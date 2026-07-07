package org.learne.platform.learneservice.domain.services.CoursesEnrollment;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.learne.platform.learneservice.domain.model.commands.CoursesEnrollment.CreateCoursesEnrollmentCommand;

import java.util.Optional;

public interface CoursesEnrollmentCommandService {
    Optional<CoursesEnrollment> handle(CreateCoursesEnrollmentCommand command);
}
