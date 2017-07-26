package com.xyj.study.domain;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xyj.study.dubbo.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/7/25.
 */
@Controller
public class UserContoller {
    @Reference(version = "1.0.0")
    UserService userService;

    @RequestMapping("/haha")
    @ResponseBody
    public User getUser(){
        return userService.getUser("hahaha");
    }

}
