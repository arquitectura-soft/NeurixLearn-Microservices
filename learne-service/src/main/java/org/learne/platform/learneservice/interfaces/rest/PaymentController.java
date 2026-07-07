package org.learne.platform.learneservice.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learneservice.domain.services.Payment.PaymentCommandService;
import org.learne.platform.learneservice.interfaces.rest.resources.Payment.CreatePaymentResource;
import org.learne.platform.learneservice.interfaces.rest.resources.Payment.PaymentResource;
import org.learne.platform.learneservice.interfaces.rest.transform.Payment.CreatePaymentCommandFromResourceAssembler;
import org.learne.platform.learneservice.interfaces.rest.transform.Payment.PaymentResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments API")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;

    public PaymentController(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    @Operation(
            summary = "Saves a Payment Method",
            description = "Saves a Payment Method for a certain User identified by their studentId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment Method Saved for User"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        var payment = paymentCommandService.handle(createPaymentCommand);
        if(payment.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var createPayment = payment.get();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(createPayment);
        return new ResponseEntity<>(paymentResource, HttpStatus.CREATED);
    }
}
