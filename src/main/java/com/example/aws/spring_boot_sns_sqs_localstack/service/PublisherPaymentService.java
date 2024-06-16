package com.example.aws.spring_boot_sns_sqs_localstack.service;

import com.example.aws.spring_boot_sns_sqs_localstack.model.Payment;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.stereotype.Service;

import static com.example.aws.spring_boot_sns_sqs_localstack.util.Constant.*;

@Service
public class PublisherPaymentService {

    private final SnsTemplate snsTemplate;

    public PublisherPaymentService(final SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    public String publishNewPayment(final Payment payment) {
        snsTemplate.sendNotification(TOPIC_SNS_NEW_PAYMENT_SNS, SnsNotification.of(payment));
        return String.format("Message published successfully. Id: %s Status %s", payment.paymentId(),
                payment.status());
    }
}
