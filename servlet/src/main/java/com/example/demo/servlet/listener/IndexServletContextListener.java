package com.example.demo.servlet.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 在 Servlet 容器 初始化 过程中，contextInitialized() 方法会被调用，在容器 销毁 时会调用 contextDestroyed()
 */
@WebListener
public class IndexServletContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServletContextListener.class);
    public static final String INITIAL_CONTENT = "Content created in servlet Context";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Start to initialize servlet context");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("content", INITIAL_CONTENT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Destroy servlet context");
    }
}

