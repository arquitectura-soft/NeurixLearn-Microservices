package org.learne.platform.learneservice.domain.services.Course;

import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.domain.model.commands.CreatedCourseCommand;

import java.util.Optional;

public interface CourseCommandService {
    Optional<Course> handle(CreatedCourseCommand command);
}
