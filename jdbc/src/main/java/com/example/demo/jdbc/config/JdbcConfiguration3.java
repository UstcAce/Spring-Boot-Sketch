package com.example.demo.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 数据源JDBC配置类
 */
//@Configuration
public class JdbcConfiguration3 {

    @Bean
    /**
     * 1. 这个注解可以直接加到方法上，这时配置信息实体类就不需要了。
     * 2. 这个注解会将配置文件中的信息通过set方法直接set到DataSource上。
     * 3. 这里就需要保证配置文件中的配置key需要和DataSource类的字段属性一致，否则就会出现问题。
     */
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource getDatasource() {
        DruidDataSource source = new DruidDataSource();
        return source;
    }
}
