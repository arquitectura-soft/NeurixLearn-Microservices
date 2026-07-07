package org.learne.platform.learneservice.application.internal.queryservices;

import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learneservice.domain.model.queries.GetCourseByIdQuery;
import org.learne.platform.learneservice.domain.services.Course.CourseQueryService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {
    private final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.id());
    }
}
