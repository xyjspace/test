package com.xyj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by banma on 2017/7/4.
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaApplication.class);
    }
}
