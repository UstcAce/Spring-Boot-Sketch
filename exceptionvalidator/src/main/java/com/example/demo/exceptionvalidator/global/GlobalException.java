package com.example.demo.exceptionvalidator.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
