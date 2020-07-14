package com.example.demo.exceptionvalidator.global;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

//@Configuration
public class GlobalException2 {
    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /**
         * 参数一：异常类型全名
         * 参数二：视图名称
         */
        properties.put("java.lang.NullPointerException","error");
        properties.put("java.lang.ArithmeticException","error");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
}
