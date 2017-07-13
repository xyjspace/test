package com.xyj.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by banma on 2017/6/21.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.xyj.shiro.mapper")
public class ShiroTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroTestApplication.class, args);
    }
}
