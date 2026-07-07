package org.learne.platform.learneservice.infrastructure.persistence.jpa;


import org.learne.platform.learneservice.domain.model.aggregates.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findById(Long id);

    boolean existsByExamIdAndQuestion(Long examId, String question);
}
