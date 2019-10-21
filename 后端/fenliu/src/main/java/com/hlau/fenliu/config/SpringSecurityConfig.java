package com.hlau.fenliu.config;

import com.hlau.fenliu.Utils.ResCode;
import com.hlau.fenliu.Utils.ResponseUtil;
import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.security.filter.CustomAuthenticationFilter;
import com.hlau.fenliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                //所有api必须有权限
                .anyRequest().
                authenticated()
                .and().
                //关闭csrf
                        csrf().
                disable().
                //关闭cors
                        cors().disable();
        //配置未登录返回json
        http.
                formLogin().
                and().
                exceptionHandling().
                authenticationEntryPoint((request, response, authException) -> {
                    ResponseUtil.RESPONSE(response, ResCode.NOLOGIN,"请先登录");
                }).and().exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            ResponseUtil.RESPONSE(response,ResCode.NOACCESS, "您没有权限");
        });
        //退出登陆配置
        http.logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
            ResponseUtil.RESPONSE(response, ResCode.SUCCESS,"退出成功");
        });
        //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionAuthenticationFailureHandler((request, response, exception) -> {
            ResponseUtil.RESPONSE(response,ResCode.SESSIONEXPIRE ,"登录失效,请重新登录");
        });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder();
        auth.authenticationProvider(authenticationProvider());
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        //登陆成功返回信息
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            ResponseUtil.RESPONSE(response, ResCode.SUCCESS,"登录成功");
        });
        //登陆失败
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
            if (exception instanceof UsernameNotFoundException)
                ResponseUtil.RESPONSE(response,ResCode.USERNAMEERROR, exception.getMessage());
            if (exception instanceof BadCredentialsException)
                ResponseUtil.RESPONSE(response,ResCode.PASSWORDERROR ,"密码错误");
        });
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    //配置抛出用户名不存在异常
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(s -> {
            User userDetail = userService.findByUserId(s);
            if (userDetail == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            return userDetail;
        });
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}