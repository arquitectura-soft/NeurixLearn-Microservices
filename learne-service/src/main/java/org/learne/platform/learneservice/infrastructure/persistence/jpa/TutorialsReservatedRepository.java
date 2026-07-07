package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialsReservatedRepository extends JpaRepository<TutorialsReservated,Long> {
    boolean existsByStudentIdAndTutorialsCoursesId(Long student_id, Long tutorialsId);
}
