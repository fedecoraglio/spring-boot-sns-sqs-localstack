package com.example.aws.spring_boot_sns_sqs_localstack.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.example.aws.spring_boot_sns_sqs_localstack.entity.PaymentEntity;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PaymentRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DynamoDBMapper dynamoDBMapper;

    public PaymentRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(final String paymentId, final String status) throws Exception {
        if (status != null) {
            log.debug("Saving payment id {} {}", paymentId, status);
            final Date createdAt = new Date();
            final String pk = paymentId + '#' + status;
            final String sk = paymentId + '#' + createdAt.getTime();
            final Date expiresAt = createExpiredAt(createdAt);
            final PaymentEntity paymentEntity = new PaymentEntity(pk, sk, paymentId, status, createdAt, expiresAt);

            this.dynamoDBMapper.save((paymentEntity));
        } else {
            log.error("Payment is null");
            throw new Exception("Payment is required");
        }
    }

    public List<PaymentEntity> findByIdAndStatus(final String paymentId, final String status) {
        final String pkValue = paymentId + '#' + status;
        final PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPk(pkValue);

        final DynamoDBQueryExpression<PaymentEntity> queryExpression = new DynamoDBQueryExpression<PaymentEntity>()
                .withHashKeyValues(paymentEntity);

        final List<PaymentEntity> itemList = dynamoDBMapper.query(PaymentEntity.class, queryExpression);
        log.debug("Found {} items", itemList.size());
        return itemList;
    }

    private Date createExpiredAt(final Date createdAt) {
        return new LocalDate(createdAt).plusDays(30).toDate();
    }
}
