package com.zhanghe.Fast.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
@Configuration
public class ShiroConfig {
	/**
     * Shiro生命周期处理器 * @return
     */
	@Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	/**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
        Filter Chain定义说明
       1、一个URL可以配置多个Filter，使用逗号分隔
       2、当设置多个过滤器时，全部验证通过，才视为通过
       3、部分过滤器可指定参数，如perms，roles
     *
     */
	@Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
	       
        // 必须设置 SecurityManager 
       shiroFilterFactoryBean.setSecurityManager(securityManager);
       //拦截器.
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
       filterChainDefinitionMap.put("/logout", "logout");
       filterChainDefinitionMap.put("/403", "anon");
       filterChainDefinitionMap.put("/ajax/loginajax", "anon");
       //filterChainDefinitionMap.put("/index", "anon");
       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
       //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
       filterChainDefinitionMap.put("/**", "authc");
       
       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
       shiroFilterFactoryBean.setLoginUrl("/login");
       // 登录成功后要跳转的链接
       shiroFilterFactoryBean.setSuccessUrl("/index");
       //未授权界面;
       shiroFilterFactoryBean.setUnauthorizedUrl("/403");
       
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
       return shiroFilterFactoryBean;
	}
	@Bean
    public SecurityManager securityManager(){
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
       securityManager.setRealm(myShiroRealm());
       securityManager.setCacheManager(ehCacheManager());
       //DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
       //defaultSessionManager.setSessionValidationInterval(60000);//间隔多久检查一次session的有效性
      // defaultSessionManager.setGlobalSessionTimeout(60000);//session有效时间
       //defaultSessionManager.setDeleteInvalidSessions(true);//是否删除无效的
     //  securityManager.setSessionManager(defaultSessionManager);
       ServletContainerSessionManager servletContainerSessionManager = new ServletContainerSessionManager();
       securityManager.setSessionManager(servletContainerSessionManager);
       return securityManager;
    }
	/**
     * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public ShiroRealm myShiroRealm(){
    	ShiroRealm shiroRealm = new ShiroRealm();
       return shiroRealm;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
      // AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
      // authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       DefaultAdvisorAutoProxyCreator s = new DefaultAdvisorAutoProxyCreator();
       s.setProxyTargetClass(true);
       return s;
    }
    //@RequiresRoles 添加了这个才会有效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
       System.out.println("ShiroConfiguration.getEhCacheManager()");
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
       return cacheManager;
    }
}
