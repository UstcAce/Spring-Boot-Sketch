# SpringBoot中异常处理与校验
## 异常处理
SpringBoot中对于异常处理提供了五种处理方式
### 1.自定义错误页面
SpringBoot默认的处理异常的机制：SpringBoot默认的已经提供了一套处理异常的机制。
一旦程序中出现了异常SpringBoot会向/error的url发送请求。在SpringBoot中提供了一个名为BasicErrorController来处理/error请求，然后跳转到默认显示异常的页面来展示异常信息。

如果我们需要将所有的异常同一跳转到自定义的错误页面，需要再src/main/resources/templates目录下
创建error.html页面。**注意 页面名称必须叫error**

### 2.通过@ExceptionHandler注解处理异常
#### 修改Controller
```
@Controller
public class UserController {
    @GetMapping("/user")
    public String addUser() {
        String str = null;
        int len = str.length();
        return "ok";
    }

    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public ModelAndView nullPointExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("err", e.toString());
        mv.setViewName("error1");
        return mv;
    }
}
```
#### 创建页面
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>error1</title>
</head>
<body>
    出错了，请联系管理员！
    <span th:text="${err}"/>
</body>
</html>
```
### 3.通过@ControllerAdvice与@ExceptionHandler注解处理异常
#### 创建全局异常处理类
```
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public ModelAndView nullPointExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("err", e.toString());
        mv.setViewName("error1");
        return mv;
    }

    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public ModelAndView arithmeticExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("err", e.toString());
        mv.setViewName("error1");
        return mv;
    }
}
```

### 4.通过 SimpleMappingExceptionResolver对象处理异常
#### 创建全局异常处理类
```
@Configuration
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
```
### 5.通过自定义 HandlerExceptionResolver 对象处理异常
#### 创建全局异常处理类
```
@Configuration
public class GlobalException3 implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        // 判断不同的异常类型，做不同的视图跳转
        if (e instanceof NullPointerException) {
            mv.setViewName("error1");
        }
        if (e instanceof ArithmeticException) {
            mv.setViewName("error1");
        }
        mv.addObject("err", e.toString());
        return mv;
    }
}
```