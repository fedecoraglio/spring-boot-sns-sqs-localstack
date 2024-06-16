package com.example.aws.spring_boot_sns_sqs_localstack.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class DynamoDbConfiguration {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${spring.cloud.aws.credentials.accessKey}")
    private String amazonAWSAccessKey;

    @Value("${spring.cloud.aws.credentials.secretKey}")
    private String amazonAWSSecretKey;

    @Value("${spring.cloud.aws.region.static}")
    private String amazonAWSRegion;


    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                amazonDynamoDBEndpoint,amazonAWSRegion)
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(amazonAWSAccessKey,amazonAWSSecretKey)
                        )
                )
                .build();
    }
}
