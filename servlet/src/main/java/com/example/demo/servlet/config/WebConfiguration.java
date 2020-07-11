package com.example.demo.servlet.config;

import com.example.demo.servlet.interceptor.FirstIndexInterceptor;
import com.example.demo.servlet.interceptor.SecondIndexInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 在 Spring Boot 中 配置拦截器 很简单，只需要实现 WebMvcConfigurer 接口，
 * 在 addInterceptors() 方法中通过 InterceptorRegistry 添加 拦截器 和 匹配路径 即可。
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstIndexInterceptor()).addPathPatterns("/index/**");
        registry.addInterceptor(new SecondIndexInterceptor()).addPathPatterns("/index/**");
        LOGGER.info("Register FirstIndexInterceptor and SecondIndexInterceptor onto InterceptorRegistry");
    }
}
