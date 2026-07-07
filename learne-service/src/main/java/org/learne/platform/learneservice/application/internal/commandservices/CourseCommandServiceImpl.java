package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.domain.model.commands.CreatedCourseCommand;
import org.learne.platform.learneservice.domain.services.Course.CourseCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> handle(CreatedCourseCommand command) {
        if (courseRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Title already exists.");
        var course = new Course(command);
        var createCourse = courseRepository.save(course);
        return Optional.of(createCourse);
    }
}
