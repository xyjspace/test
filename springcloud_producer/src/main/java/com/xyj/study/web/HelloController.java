package com.xyj.study.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by banma on 2017/7/4.
 */
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello Spring Cloud";
    }

    @RequestMapping("/hello2")
    public String printHost(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("/hello, host: " + instance.getHost() + "，service_id: " + instance.getServiceId());
        return "Hello Spring Cloud";
    }

}
