package com.example.demo.servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 一个 Servlet 请求可以经由多个 Filter 进行过滤，最终由 Servlet 处理并响应客户端。
 *
 * 当 FirstIndexFilter 初始化时，会执行 init() 方法。每次请求路径匹配 urlPatterns 配置的路径时，
 * 就会进入 doFilter() 方法进行具体的 请求 和 响应过滤。
 */
@WebFilter(filterName = "firstIndexFilter",
        displayName = "firstIndexFilter",
        urlPatterns = {"/index/*"},
        initParams = @WebInitParam(
                name = "firstIndexFilterInitParam",
                value = "io.ostenant.springboot.sample.filter.FirstIndexFilter")
)
public class FirstIndexFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstIndexFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Register a new filter {}", filterConfig.getFilterName());
    }

    /**
     * ServletRequest: 未到达 Servlet 的 HTTP 请求；
     * ServletResponse: 由 Servlet 处理并生成的 HTTP 响应；
     * FilterChain: 过滤器链 对象，可以按顺序注册多个 过滤器。
     *
     * 一个 过滤器链 对象可以按顺序注册多个 过滤器。
     * 符合当前过滤器过滤条件，即请求 过滤成功 直接放行，则交由下一个 过滤器 进行处理。
     * 所有请求过滤完成以后，由 IndexHttpServlet 处理并生成 响应，然后在 过滤器链 以相反的方向对 响应 进行后置过滤处理。
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("FirstIndexFilter pre filter the request");
        String filter = request.getParameter("filter1");
        if (isEmpty(filter)) {
            response.getWriter().println("Filtered by firstIndexFilter, " +
                    "please set request parameter \"filter1\"");
            return;
        }
        chain.doFilter(request, response);
        LOGGER.info("FirstIndexFilter post filter the response");
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroy filter {}", getClass().getName());
    }
}

