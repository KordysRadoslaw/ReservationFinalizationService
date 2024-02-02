package com.restaurationaws.reservationfinalizationservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import io.micrometer.common.util.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.restaurationaws.reservationfinalizationservice.repositories")
public class DynamoDBConfig {

    @Value("${AWS_ACCESS_KEY_ID}")
    private String awsAccessKeyId;

    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String awsSecretAccessKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAwsCredentials());

        return amazonDynamoDB;
    }

    public AWSCredentials amazonAwsCredentials(){
        return new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
    }
}
