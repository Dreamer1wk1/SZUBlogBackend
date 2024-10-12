package com.dreamer.demoproject.config;

import com.dreamer.demoproject.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        //不拦截登录和注册接口
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
    }

}
