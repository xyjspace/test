package com.xyj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by banma on 2017/7/4.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProducerApplication.class);
    }
}
