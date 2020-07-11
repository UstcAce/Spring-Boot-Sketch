package com.example.demo.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RageController {
    /**
     * 直接访问http://localhost:8080/index.html
     * 也可访问静态资源
     */
    @RequestMapping("/page")
    public String showPage() {
        return "index.html";
    }
}
