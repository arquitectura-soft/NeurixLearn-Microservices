package org.learne.platform.learneservice.interfaces.rest.transform.Payment;

import org.learne.platform.learneservice.domain.model.aggregates.Payment;
import org.learne.platform.learneservice.interfaces.rest.resources.Payment.PaymentResource;

public class PaymentResourceFromEntityAssembler {

    public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getNameCard(),
                entity.getNumberCard(),
                entity.getExpireDate(),
                entity.getSecurityCode(),
                entity.getEmailAddress().email(),
                entity.getStudentId()
        );
    }
}
