package com.xyj.shiro.service.impl;

import com.xyj.shiro.domain.entity.User;
import com.xyj.shiro.domain.form.UserForm;
import com.xyj.shiro.mapper.UserMapper;
import com.xyj.shiro.service.UserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by banma on 2017/6/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public boolean addUser(UserForm userForm) {
        User user = new User();
        try {
            PropertyUtils.copyProperties(user, userForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userMapper.addUser(user);
    }
}