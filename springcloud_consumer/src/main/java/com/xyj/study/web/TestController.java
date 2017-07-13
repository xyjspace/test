package com.xyj.study.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/7/8.
 */
@Controller
@RefreshScope
public class TestController {

    @Value("${from}")
    private String from;

    @ResponseBody
    @RequestMapping("helloConfiguration")
    public String from(){
        return this.from;
    }

}
