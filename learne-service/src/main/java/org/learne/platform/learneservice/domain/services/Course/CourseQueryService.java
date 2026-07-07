package org.learne.platform.learneservice.domain.services.Course;

import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learneservice.domain.model.queries.GetCourseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
    List<Course> handle(GetAllCoursesQuery query);

    Optional<Course> handle(GetCourseByIdQuery query);
}
