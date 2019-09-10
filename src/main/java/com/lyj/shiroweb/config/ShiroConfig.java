package com.lyj.shiroweb.config;

import com.lyj.shiroweb.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lyj
 * @date 2019/9/6 23:42
 */
@SpringBootConfiguration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManagerByRealm());
        // 登入的url
        factoryBean.setLoginUrl("/subLogin");
        // 未认证的跳转
        factoryBean.setUnauthorizedUrl("/403");
        //权限控制map
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/uploadFiles","anon");
        filterChainDefinitionMap.put("/subLogin", "anon");
        filterChainDefinitionMap.put("/testRole","perms['xxx']");
        filterChainDefinitionMap.put("/*", "authc");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManagerByRealm(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getCustomRealm());
        return securityManager;
    }

    @Bean
    public CustomRealm getCustomRealm(){
        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(hashPassword());
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher hashPassword(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }
    /**
     * 开启shiro的aop，即可以使用shiro注解
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor shiroAop(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getSecurityManagerByRealm());
        return advisor;
    }
}
