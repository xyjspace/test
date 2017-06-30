package com.xyj.shiro.util;

import com.xyj.shiro.domain.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by banma on 2017/6/30.
 */
public class PasswordHelper {

    private static PasswordHelper passwordHelper = null;

    private PasswordHelper() {
    }

    private String algorithmName = "MD5";

    private final int hashIterations = 2;

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        //shiro的通用散列，
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);

    }

    //双重检测的懒汉模式
    public static PasswordHelper getPasswordHelper() {

        if (passwordHelper == null) {
            synchronized (PasswordHelper.class) {
                if(passwordHelper == null) {
                    return new PasswordHelper();
                }
            }
        }
        return passwordHelper;

    }


}
