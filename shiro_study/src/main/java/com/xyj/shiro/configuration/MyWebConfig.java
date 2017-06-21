package com.xyj.shiro.configuration;

import com.xyj.shiro.inteceptor.VerifyInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by banma on 2017/6/21.
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyInteceptor());
    }
}
