package org.learne.platform.learneservice.domain.services.Payment;

import org.learne.platform.learneservice.domain.model.aggregates.Payment;
import org.learne.platform.learneservice.domain.model.commands.CreatePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
}
