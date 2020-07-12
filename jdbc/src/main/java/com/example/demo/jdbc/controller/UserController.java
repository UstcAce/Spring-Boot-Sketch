package com.example.demo.jdbc.controller;

import com.example.demo.jdbc.pojo.Users;
import com.example.demo.jdbc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/addUser")
    public String addUser(Users users) {
        try {
            this.usersService.addUser(users);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        // TODO: 重定向的背景知识
        return "redirect:/ok";
    }

    @GetMapping("/findUserAll")
    public String findUserAll(Model model) {
        List<Users> list = this.usersService.findAllUsers();
        model.addAttribute("list", list);
        return "showUsers";
    }

    @GetMapping("/preUpdateUser")
    public String preUpdateUser(Integer id, Model model) {
        Users users = usersService.findUserById(id);
        model.addAttribute("user", users);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(Users users) {
        try {
            this.usersService.modifyUser(users);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        // TODO: 重定向的背景知识
        return "redirect:/ok";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Integer id) {
        try {
            this.usersService.dropUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        // TODO: 重定向的背景知识
        return "redirect:/ok";
    }
}
