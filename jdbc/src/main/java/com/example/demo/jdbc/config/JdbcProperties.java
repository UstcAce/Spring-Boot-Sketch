package com.example.demo.jdbc.config;


/**
 * Spring Boot的注解，只能读取application配置文件
 * 通过set方法进行属性的填充，注意属性的名称需要和配置文件中的属性名称一致
 */
//@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
