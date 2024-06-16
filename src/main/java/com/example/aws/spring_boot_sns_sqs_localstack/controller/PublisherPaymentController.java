package com.example.aws.spring_boot_sns_sqs_localstack.controller;

import com.example.aws.spring_boot_sns_sqs_localstack.controller.request.NotifyPaymentStatus;
import com.example.aws.spring_boot_sns_sqs_localstack.model.Payment;
import com.example.aws.spring_boot_sns_sqs_localstack.service.PublisherPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/payments/publishing")
public class PublisherPaymentController {

    final PublisherPaymentService publisherPaymentService;

    public PublisherPaymentController(final PublisherPaymentService publisherPaymentService) {
        this.publisherPaymentService = publisherPaymentService;
    }

    @PostMapping()
    public ResponseEntity<String> publishMessage(@RequestBody final NotifyPaymentStatus notify) {
        if (notify == null || notify.getPaymentId() == null || notify.getPaymentId().isEmpty() ||
                notify.getPaymentStatus() == null || notify.getPaymentStatus().isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Id(payment_id) and status(payment_status)");
        }
        return ResponseEntity.ok(publisherPaymentService.publishNewPayment(createPayment(notify)));
    }

    private Payment createPayment(final NotifyPaymentStatus notifyPaymentStatus) {
        return new Payment(notifyPaymentStatus.getPaymentId(), notifyPaymentStatus.getPaymentStatus().toLowerCase());
    }
}
