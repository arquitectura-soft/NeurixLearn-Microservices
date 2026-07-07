package org.learne.platform.learneservice.domain.services.TutorialsCourses;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsCourses;
import org.learne.platform.learneservice.domain.model.queries.TutorialsCourses.GetAllTutorialsCoursesQuery;
import org.learne.platform.learneservice.domain.model.queries.TutorialsCourses.GetTutorialsCoursesByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TutorialsCoursesQueryService {
    Optional<TutorialsCourses> handle(GetTutorialsCoursesByIdQuery query);
    List<TutorialsCourses> handle(GetAllTutorialsCoursesQuery query);
}
