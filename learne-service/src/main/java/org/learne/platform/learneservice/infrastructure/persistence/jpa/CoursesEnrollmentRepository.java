package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesEnrollmentRepository extends JpaRepository<CoursesEnrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long course);
}
