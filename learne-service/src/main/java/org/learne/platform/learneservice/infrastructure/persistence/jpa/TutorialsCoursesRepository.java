package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorialsCoursesRepository extends JpaRepository<TutorialsCourses, Long> {
    Optional<TutorialsCourses> findById(Long id);

    boolean existsByCourseIdAndDateAndHour(Long course, String date, String hour);
}
