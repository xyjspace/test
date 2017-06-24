package com.xyj.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by banma on 2017/6/21.
 */
@SpringBootApplication
//@ServletComponentScan
public class ShiroTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroTestApplication.class, args);
    }
}
