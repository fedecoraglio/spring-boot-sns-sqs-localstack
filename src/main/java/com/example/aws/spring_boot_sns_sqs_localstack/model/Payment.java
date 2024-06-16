package com.example.aws.spring_boot_sns_sqs_localstack.model;

import java.io.Serializable;

public record Payment(String paymentId, String status) implements Serializable {

}
