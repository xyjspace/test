package com.xyj.shiro.web;

import com.xyj.shiro.annotation.CrmPermission;
import com.xyj.shiro.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/6/21.
 */
@Controller
public class TestWeb {

    @CrmPermission("test2")
    @ResponseBody
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public User test1(){
        User user = new User();
        user.setName("xunyajie");
        return user;
    }

}
