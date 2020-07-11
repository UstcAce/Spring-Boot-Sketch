原文：https://juejin.im/post/5b2ddbcef265da59a76c92a4#heading-23

#### 监听器Listener
Listener可以监听 web 服务器中某一个**事件操作**，并触发注册的 **回调函数**。通俗的语言就是在 application，session，request 三个对**象创建/消亡或者增删改属性**时，自动执行代码的功能组件。

#### Servlet
Servlet是一种运行**服务器端**的java应用程序，具有**独立于平台和协议 的特性**，并且可以动态的生成web页面，它工作在**客户端请求与服务器响应的中间层**。

#### 过滤器Filter
Filter对用户请求进行**预处理**，接着将请求交给Servlet进行**处理并生成响应**，最后 Filter再对**服务器响应进行后处理**。Filter是可以复用的代码片段，常用来**转换 HTTP请求、响应和头信息**。**Filter不像Servlet，它不能产生响应，而是只修改对某一资源的请求或者响应。**

#### 拦截器Interceptor
类似面向切面编程中的切面和通知，我们通过**动态代理**对一个service()方法添加通知 进行功能增强。比如说在**方法执行前进行初始化处理，在方法执行后进行后置处理**。拦截器的思想和AOP类似，区别就是**拦截器只能对Controller的HTTP请求进行拦截**。


#### Filter和Interceptor的对比
##### 两者的区别
1. Filter 是基于 函数回调的，而 Interceptor 则是基于 Java 反射 和 动态代理。

2. Filter 依赖于 Servlet 容器，而 Interceptor 不依赖于 Servlet 容器。

3. Filter 对几乎 所有的请求 起作用，而 Interceptor 只对 Controller 对请求起作用。

##### 执行顺序
**对于自定义 Servlet 对请求分发流程：**

1. Filter 过滤请求处理；
2. Servlet 处理请求；
3. Filter 过滤响应处理。

**对于自定义 Controller 的请求分发流程：**

1. Filter 过滤请求处理；
2. Interceptor 拦截请求处理；
3. 对应的 HandlerAdapter 处理请求；
4. Interceptor 拦截响应处理；
5. Interceptor 的最终处理；
6. Filter 过滤响应处理。