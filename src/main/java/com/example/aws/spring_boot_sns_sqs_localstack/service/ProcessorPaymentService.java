package com.example.aws.spring_boot_sns_sqs_localstack.service;

import com.example.aws.spring_boot_sns_sqs_localstack.entity.PaymentEntity;
import com.example.aws.spring_boot_sns_sqs_localstack.model.Payment;
import com.example.aws.spring_boot_sns_sqs_localstack.repository.PaymentRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.aws.spring_boot_sns_sqs_localstack.util.Constant.QUEUE_NOTIFICATION_PAYMENT;

@Service
public class ProcessorPaymentService {
    Logger logger = LoggerFactory.getLogger(ProcessorPaymentService.class);
    private final PaymentRepository paymentRepository;

    public ProcessorPaymentService(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @SqsListener(QUEUE_NOTIFICATION_PAYMENT)
    public void processPayment(final Payment payment) throws Exception {
        try {
            logger.info("Message payment received - {}", payment);
            final List<PaymentEntity> result = this.paymentRepository.findByIdAndStatus(payment.paymentId(),
                    payment.status());
            if (result.isEmpty()) {
                this.paymentRepository.save(payment.paymentId(), payment.status());
            } else {
                logger.warn("Message payment was sent - {}", payment);
            }
        } catch (final Exception ex) {
            logger.error("Error processing payment {}", payment, ex);
            throw ex;
        }
    }

}
