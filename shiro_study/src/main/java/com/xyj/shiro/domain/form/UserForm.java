package com.xyj.shiro.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by banma on 2017/6/24.
 */
@Data
public class UserForm implements Serializable{
    private String name;
    private Integer age;
    private String password;
}
