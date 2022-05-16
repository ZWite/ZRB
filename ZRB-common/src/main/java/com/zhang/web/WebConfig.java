package com.zhang.web;


import com.zhang.Interceptor.AuthenticationInterceptor;
import com.zhang.Interceptor.UserThreadLocal;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 解决跨域问题
     * @Method addCorsMappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.
                //所有的当前站点的请求地址，都支持跨域访问。
                        addMapping("/**")
                //当前站点支持的跨域请求类型是什么
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 是否支持跨域用户凭证
                .allowCredentials(true)
                //有的外部域都可跨域访问。
                .allowedOrigins("*")
                // 超时时长设置为1小时。 时间单位是秒。
                .maxAge(60);
    }

    /**
     * 设置多拦截器拦截顺序
     * @Method addInterceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
        UserThreadLocal userThreadLocal = new UserThreadLocal();
        registry.addInterceptor(userThreadLocal);
        registry.addInterceptor(authenticationInterceptor);
    }

    public static void main(String[] args) {
        try {
            int i = 1/0;
        } catch (Exception e){
            System.out.println("!");
            System.exit(0);
        } finally {
            System.out.println(2);
        }
    }

}
