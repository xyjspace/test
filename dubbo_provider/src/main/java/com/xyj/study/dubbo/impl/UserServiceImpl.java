package com.xyj.study.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xyj.study.domain.User;
import com.xyj.study.dubbo.UserService;

/**
 * Created by banma on 2017/7/25.
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService{

    @Override
    public User getUser(String name) {
        User user = new User();
        user.setName(name);
        user.setAge(16);
        return user;
    }
}
