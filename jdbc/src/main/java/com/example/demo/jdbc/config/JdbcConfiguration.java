package com.example.demo.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 数据源JDBC配置类
 */
// 这里注释掉，使其不生效
//@Configuration
/**
 * Spring Springframework的注解，加载任何一个指定的properties配置文件
 */
@PropertySource("classpath:/jdbc.properties")
public class JdbcConfiguration {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 实例化数据源对象
     */
    @Bean
    public DataSource getDatasource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(this.driverClassName);
        source.setUrl(this.url);
        source.setUsername(this.username);
        source.setPassword(this.password);
        return source;
    }
}
