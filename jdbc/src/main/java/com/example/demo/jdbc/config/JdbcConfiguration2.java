package com.example.demo.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 数据源JDBC配置类
 */
//@Configuration
// 指定加载那个配置属性类
//@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration2 {

    // 方法一：通过@Autowired注解注入
    @Autowired
    private JdbcProperties jdbcProperties;

    // 方法二：也可以通过构造方法注入
//    private JdbcProperties jdbcProperties;
//    public JdbcConfiguration2(JdbcProperties jdbcProperties) {
//        this.jdbcProperties = jdbcProperties;
//    }

    /**
     * 实例化数据源对象
     */
    @Bean
    public DataSource getDatasource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(this.jdbcProperties.getDriverClassName());
        source.setUrl(this.jdbcProperties.getUrl());
        source.setUsername(this.jdbcProperties.getUsername());
        source.setPassword(this.jdbcProperties.getPassword());
        return source;
    }

    // 方法三：也可以通过函数型参注入
//    @Bean
//    public DataSource getDatasource(JdbcProperties jdbcProperties) {
//        DruidDataSource source = new DruidDataSource();
//        source.setDriverClassName(jdbcProperties.getDriverClassName());
//        source.setUrl(jdbcProperties.getUrl());
//        source.setUsername(jdbcProperties.getUsername());
//        source.setPassword(jdbcProperties.getPassword());
//        return source;
//    }
}
