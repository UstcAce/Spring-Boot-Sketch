package com.example.demo.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

import static java.lang.System.out;

@Controller
public class DatasourceController {

    @Autowired
    private DataSource dataSource;

    @ResponseBody
    @RequestMapping("/datasource")
    public String showDataSourceInfo() {
        out.println(dataSource.toString());
        out.println(dataSource.getClass().getPackage());
        return "ok";
    }
}
