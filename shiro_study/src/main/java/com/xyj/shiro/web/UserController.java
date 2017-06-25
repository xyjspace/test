package com.xyj.shiro.web;

import com.xyj.shiro.domain.form.UserForm;
import com.xyj.shiro.service.UserService;
import com.xyj.shiro.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

/**
 * Created by banma on 2017/6/25.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    public void addUser(UserForm userForm) {
        userService.addUser(userForm);
        LogUtils.logger.info("插入用戶數據成功");
    }

}
