package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.Payment;
import org.learne.platform.learneservice.domain.model.commands.CreatePaymentCommand;
import org.learne.platform.learneservice.domain.services.Payment.PaymentCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        if (paymentRepository.existsPaymentByStudentId(command.studentId())){
            throw new IllegalArgumentException("Payment for student already exists");
        }
        var payment = new Payment(command);
        paymentRepository.save(payment);
        return Optional.of(payment);
    }
}
