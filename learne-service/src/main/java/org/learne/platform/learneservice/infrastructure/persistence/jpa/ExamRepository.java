package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findById(Long id);

    boolean existsByCourseIdAndTitle(Long id, String title);
}
