package org.learne.platform.learneservice.infrastructure.persistence.jpa;

import org.learne.platform.learneservice.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsPaymentByStudentId(Long StudentId);
}
