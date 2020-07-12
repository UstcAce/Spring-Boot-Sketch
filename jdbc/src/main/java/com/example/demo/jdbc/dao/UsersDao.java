package com.example.demo.jdbc.dao;

import com.example.demo.jdbc.pojo.Users;

import java.util.List;

public interface UsersDao {
    void insertUsers(Users users);

    List<Users> selectUserAll();

    Users selectUserById(Integer id);

    void updateUser(Users users);

    void deleteUser(Integer id);
}
