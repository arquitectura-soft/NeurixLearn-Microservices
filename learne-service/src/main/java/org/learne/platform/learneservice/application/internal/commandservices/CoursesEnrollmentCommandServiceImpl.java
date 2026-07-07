package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.learne.platform.learneservice.domain.model.commands.CoursesEnrollment.CreateCoursesEnrollmentCommand;
import org.learne.platform.learneservice.domain.services.CoursesEnrollment.CoursesEnrollmentCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CoursesEnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesEnrollmentCommandServiceImpl implements CoursesEnrollmentCommandService {
    private final CoursesEnrollmentRepository coursesEnrollmentRepository;
    public CoursesEnrollmentCommandServiceImpl(CoursesEnrollmentRepository coursesEnrollmentRepository) {
        this.coursesEnrollmentRepository = coursesEnrollmentRepository;
    }
    @Override
    public Optional<CoursesEnrollment> handle(CreateCoursesEnrollmentCommand command) {
        if (coursesEnrollmentRepository.existsByStudentIdAndCourseId(command.studentId(), command.courseId())) {
            throw new IllegalArgumentException("Student Id and Course Id is already exists");
        }
        var coursesEnrollment = new CoursesEnrollment(command);
        var createCoursesEnrollment = coursesEnrollmentRepository.save(coursesEnrollment);
        return Optional.of(createCoursesEnrollment);
    }
}
