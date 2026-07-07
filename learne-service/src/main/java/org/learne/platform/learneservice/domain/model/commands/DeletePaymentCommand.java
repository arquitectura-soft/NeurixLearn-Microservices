package org.learne.platform.learneservice.domain.model.commands;

public record DeletePaymentCommand(Long paymentId) {
    public DeletePaymentCommand {
        if (paymentId == null || paymentId <= 0) {
            throw new IllegalArgumentException("Invalid payment id");
        }
    }
}
