package org.learne.platform.learneservice.application.internal.queryservices;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.learne.platform.learneservice.domain.model.queries.CoursesEnrollment.GetAllCoursesEnrollmentQuery;
import org.learne.platform.learneservice.domain.services.CoursesEnrollment.CoursesEnrollmentQueryService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.CoursesEnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesEnrollmentQueryServiceImpl implements CoursesEnrollmentQueryService {
    private final CoursesEnrollmentRepository coursesEnrollmentRepository;
    public CoursesEnrollmentQueryServiceImpl(CoursesEnrollmentRepository coursesEnrollmentRepository) {
        this.coursesEnrollmentRepository = coursesEnrollmentRepository;
    }
    @Override
    public List<CoursesEnrollment> handle(GetAllCoursesEnrollmentQuery query) {
        return coursesEnrollmentRepository.findAll();
    }
}
