package com.xyj.shiro.shiro.realm;

import com.xyj.shiro.domain.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.assertj.core.util.Sets;

/**
 * Created by banma on 2017/6/30.
 */
public class UserReam_6 extends AuthorizingRealm {

    /**
     * 设置用户的角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //TODO 设置用户的角色
        authorizationInfo.setRoles(Sets.newHashSet());
        //TODO 设置用户的权限
        authorizationInfo.setStringPermissions(Sets.newHashSet());

        return authorizationInfo;
    }

    /**
     * 验证用户的信息是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        //TODO 获取用户实体
        User user = new User();
//        User user = userService.findByUserName();想
        if(user == null){
            throw new UnknownAccountException();
        }
        //进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName());
        return authenticationInfo;
    }
}
