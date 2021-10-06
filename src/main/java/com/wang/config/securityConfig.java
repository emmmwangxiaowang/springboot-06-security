package com.wang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: 王航
 * @Email: 954544828@qq.com
 * @Date: 2021/10/6 0006
 */

//AOP 拦截器
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人都可以访问,功能也需要对应的权限
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认会到登录页面
        //需要开启登录的页面
        http.formLogin();

        //防止网站攻击
        //关闭csrf springboot默认开启  跨站点请求伪造
        http.csrf().disable();

        //开启注销功能
        http.logout().deleteCookies("remove").invalidateHttpSession(false).logoutSuccessUrl("/");
    }

    //认证
    //密码编码: PasswordEncoding
    //在spring security5.0+ 中新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据正常应该从数据库中读取  这里使用的是内存数据inMemoryAuthentication
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("wang").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and().withUser("yingying").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip1");
    }
}
