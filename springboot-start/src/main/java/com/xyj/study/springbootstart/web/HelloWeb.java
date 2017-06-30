package com.xyj.study.springbootstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/6/28.
 */
@Controller
public class HelloWeb {

    @ResponseBody
    @RequestMapping("/hello")
    public String helloTest(){
        return "hello spring-boot";
    }

}
