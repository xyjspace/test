package com.xyj.shiro.domain.entity;

import lombok.Data;

/**
 * Created by banma on 2017/6/30.
 */
@Data
public class PermissionForm {

    private Long id;

    private String permission;

    private String desc;

    private Integer status;

}
