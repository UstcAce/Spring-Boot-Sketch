package com.example.demo.mybatis.service;


import com.example.demo.mybatis.pojo.Users;

import java.util.List;

public interface UsersService {
    void addUser(Users users);

    List<Users> findAllUsers();

    Users findUserById(Integer id);

    void modifyUser(Users users);

    void dropUser(Integer id);
}
