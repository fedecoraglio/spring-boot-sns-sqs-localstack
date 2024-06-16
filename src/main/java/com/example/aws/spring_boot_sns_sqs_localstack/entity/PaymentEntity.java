package com.example.aws.spring_boot_sns_sqs_localstack.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable(tableName = "paymentNotification")
public class PaymentEntity {

    @DynamoDBHashKey
    private String pk;
    @DynamoDBRangeKey
    private String sk;
    @DynamoDBAttribute
    private String paymentId;
    @DynamoDBAttribute
    private String status;
    @DynamoDBAttribute
    private Date createdAt;
    @DynamoDBAttribute
    private Date expiredAt;

    public PaymentEntity(final String pk, final String sk, final String paymentId, final String status,
                         final Date createdAt, final Date expiredAt) {
        this.pk = pk;
        this.sk = sk;
        this.paymentId = paymentId;
        this.status = status;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public PaymentEntity() {}

    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public String getPk() {
        return pk;
    }

    public String getSk() {
        return sk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiredAt(Date createdAt) {
        return expiredAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
