package com.xyj.shiro.shiro;

import com.xyj.shiro.domain.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banma on 2017/6/20.
 * 用户需要提供一份principals 和 credentials 给shiro来验证
 */
public class LoginTest {
    @Test
    public void testHelloWorld(){
        //1.获取securityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到securityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名，密码身份验证token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            System.out.printf(String.valueOf(subject.isAuthenticated()));
            //4.subject.login()方法自动委托给SecurityManager.login()方法。
            subject.login(token);
            System.out.printf(String.valueOf(subject.isAuthenticated()));
        } catch (AuthenticationException e) {
            System.out.printf("验证过程出错");
        }

        subject.logout();

    }

    @Test
    public void test2(){
        List<User> list = new ArrayList<>();

        List<String> name = new ArrayList();
        name.add("haha");
        name.add("xixi");

        User  user = new User();
        user.setAge(10);
        user.setPassword("aaaaaa");
        list.add(user);
        for (String name2 : name) {
            user.setName(name2);
            list.add(user);
        }

        System.out.println(list);


    }

}
