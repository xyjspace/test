package com.xyj.study;

import com.xyj.study.sys.TulingEntity;
import com.xyj.study.sys.WeixinEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by banma on 2017/10/31.
 */
@SpringBootApplication
@EnableConfigurationProperties({WeixinEntity.class, TulingEntity.class})
public class WeixinRobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeixinRobotApplication.class);
    }
}
