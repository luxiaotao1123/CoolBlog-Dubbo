package cn.dubbo.shiro;

import cn.dubbo.common.bean.User;
import cn.dubbo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String name = principals.toString();
        info.addRole("admin");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = (String) usernamePasswordToken.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        int status = userService.queryUser(new User(username,password));
        if (status==500){
            System.out.println("用户名密码错误");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
