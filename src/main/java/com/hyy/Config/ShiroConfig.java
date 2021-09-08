package com.hyy.Config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactorBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
           anon:无需认证就可以访问
           authc:必须认证才能访问
           user:必须拥有 记住我 功能才能用
           perms: 拥有对某个资源的权限才能访问
           roles: 拥有某个角色权限
         */


        Map<String, String> filterMap =new LinkedHashMap<>();


        //授权  没有授权会跳转到未授权页面
        filterMap.put("/index","perms[user:index]");
        filterMap.put("/login","anon");
//        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/*","authc");
        //拦截

////                filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        bean.setUnauthorizedUrl("/noauth");


        //设置登录请求
        bean.setLoginUrl("/toLogin");

        return bean;
    }

    //DefaultWebSecurityManger
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //创建 realm对象， 需要自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm()
    {
        return  new UserRealm();
    }

    //整合shiroDialect: 用来整合shiro 和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
