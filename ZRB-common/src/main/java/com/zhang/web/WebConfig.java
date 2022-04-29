package com.zhang.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.
                //所有的当前站点的请求地址，都支持跨域访问。
                addMapping("/**")
                //当前站点支持的跨域请求类型是什么
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                // 是否支持跨域用户凭证
                .allowCredentials(true)
                //有的外部域都可跨域访问。
                .allowedOrigins("*")
                // 超时时长设置为1小时。 时间单位是秒。
                .maxAge(60);
    }
}
