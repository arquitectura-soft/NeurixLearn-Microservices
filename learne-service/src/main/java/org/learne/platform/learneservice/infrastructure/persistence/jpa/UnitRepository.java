package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findById(Long id);
}
