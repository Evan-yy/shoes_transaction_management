package com.hyy.Config;

import com.hyy.pojo.User;
import com.hyy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权=》AuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        info.addStringPermission("user:add");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser =  (User)subject.getPrincipal(); //拿到User对象
        if(currentUser.getPerms()==null)
        { return null;}
        //设置当前用户权限
        //** 多个权限用|
        else {
            info.addStringPermission(currentUser.getPerms());
            return info;
        }
    }



    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证=》AuthenticationInfo");

        //用户名，密码~   数据库中取
//        String name="root";
//        String password="123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //连接真真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){
            //没有这个人
            return null;
        }

        //            subject.getSession();

        //Md5CredentialsMatcher
        //密码可以加密 ：  MD5  MD5盐值加密
        //密码认证 ,shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
