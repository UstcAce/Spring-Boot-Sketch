## Spring Boot整合JDBC

### 通过自定义配置文件的方式配置数据源

#### 通过@PropertySource注解读取配置文件
Spring Springframework的注解
```
@Configuration
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
```

#### 通过@ConfigurationProperties注解读取配置文件
Spring Boot的注解
- 创建配置信息实体类: 这里有三种方法进行配置信息实体类的注入
```
/**
 * Spring Boot的注解，只能读取application配置文件
 * 通过set方法进行属性的填充，注意属性的名称需要和配置文件中的属性名称一致
 */
@ConfigurationProperties(prefix = "jdbc")
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
```
- 修改配置类
```
@Configuration
// 指定加载那个配置属性类
@EnableConfigurationProperties(JdbcProperties.class)
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
```
#### 通过@ConfigurationProperties注解读取配置文件的一种更优雅的使用方式
```
@Configuration
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
```
### 通过Spring Boot文件的方式配置数据源
在Spring Boot 2.x 版本中spring-boot-starter.jdbc启动器中默认使用的是com.zaxxer.hikariDataSource作为数据源
#### 通过Spring Boot默认的HikariDataSource数据源
```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
```

#### 通过Spring Boot第三方的Druid数据源
```
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
```