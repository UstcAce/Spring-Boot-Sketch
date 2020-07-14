package com.example.demo.exceptionvalidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String addUser() {
        String str = null;
        int len = str.length();
        return "ok";
    }

    @GetMapping("/user2")
    public String addUser2() {
        String str = null;
        int len = 1/0;
        return "ok";
    }

//    @ExceptionHandler(value = {java.lang.NullPointerException.class})
//    public ModelAndView nullPointExceptionHandler(Exception e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("err", e.toString());
//        mv.setViewName("error1");
//        return mv;
//    }
}
