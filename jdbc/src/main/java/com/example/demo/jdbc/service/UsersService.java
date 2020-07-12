package com.example.demo.jdbc.service;

import com.example.demo.jdbc.pojo.Users;

import java.util.List;

public interface UsersService {
    void addUser(Users users);

    List<Users> findAllUsers();

    Users findUserById(Integer id);

    void modifyUser(Users users);

    void dropUser(Integer id);
}
