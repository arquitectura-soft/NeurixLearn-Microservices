package org.learne.platform.profileservice.infrastructure.persistence.jpa;

import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    boolean existsByUsernameAndEmail(String username, String email);
}
