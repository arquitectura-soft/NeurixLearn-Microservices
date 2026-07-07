package org.learne.platform.learneservice.domain.services.CoursesEnrollment;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.learne.platform.learneservice.domain.model.queries.CoursesEnrollment.GetAllCoursesEnrollmentQuery;

import java.util.List;

public interface CoursesEnrollmentQueryService {
    List<CoursesEnrollment> handle(GetAllCoursesEnrollmentQuery query);
}
