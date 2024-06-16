package com.example.aws.spring_boot_sns_sqs_localstack.controller.request;

public class NotifyPaymentStatus {

    private String paymentId;
    private String paymentStatus;

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
