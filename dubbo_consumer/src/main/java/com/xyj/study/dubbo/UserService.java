package com.xyj.study.dubbo;

import com.xyj.study.domain.User;

/**
 * Created by banma on 2017/7/25.
 */
public interface UserService {
    User getUser(String name);
}
