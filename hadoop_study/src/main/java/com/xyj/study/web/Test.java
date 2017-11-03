package com.xyj.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/10/31.
 */
@Controller
public class Test {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "123";
    }
}
