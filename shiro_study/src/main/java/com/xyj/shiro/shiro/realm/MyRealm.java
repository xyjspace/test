package com.xyj.shiro.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.rmi.activation.UnknownObjectException;

/**
 * Created by banma on 2017/6/21.
 */
public class MyRealm implements Realm{

    @Override
    public String getName() {
        return "testRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(userName)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        //如果数据验证成功则返回一个AuthenticationIndo实现
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
