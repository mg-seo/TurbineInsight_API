package com.yinseo.turbineInsightWeb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("YOUR_ACCESS_KEY_ID", "YOUR_SECRET_ACCESS_KEY");
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2) // 설정한 리전으로 변경하세요
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}