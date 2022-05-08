package com.zhang.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: diexi
 * @Date: 2022/5/8 12:50
 * @ClassName: webConfig
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        AuthenticationInterceptor authenticationInterceptor  = new AuthenticationInterceptor();
        UserThreadLocal userThreadLocal = new UserThreadLocal();
        registry.addInterceptor(userThreadLocal);
        registry.addInterceptor(authenticationInterceptor);
    }
}
