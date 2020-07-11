## Spring Boot访问资源位置
Spring Boot默认在static目录中存放静态页面，而在templates中存放动态页面Thymeleaf。

**static目录**：
Spring Boot通过classpath(resource)/static目录访问静态资源(html,js,css)。注意**存放静态资源的目录名称必须是static**。

**templates目录**：
在Spring Boot中不推荐使用jsp技术作为视图层技术，而是默认使用Themeleaf来做动态页面。**Templates目录就是存放这Thymeleaf的页面**。


### Spring Boot访问静态资源的位置
查找顺序如下
- classpath(resources):/META-INF/resources/
- classpath(resources):/resources/
- classpath(resources):/static/
- classpath(resources):/public/

### 自定义静态资源的位置
配置完成就会导致上面默认的查找顺序失效
```
spring.resources.static-locations=classpath:/suibian/
```