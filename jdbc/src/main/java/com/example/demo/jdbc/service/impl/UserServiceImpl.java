package com.example.demo.jdbc.service.impl;

import com.example.demo.jdbc.dao.UsersDao;
import com.example.demo.jdbc.pojo.Users;
import com.example.demo.jdbc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层
 */
@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void addUser(Users users) {
        usersDao.insertUsers(users);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersDao.selectUserAll();
    }

    @Override
    public Users findUserById(Integer id) {
        return usersDao.selectUserById(id);
    }

    @Override
    @Transactional
    public void modifyUser(Users users) {
        usersDao.updateUser(users);
    }

    @Override
    @Transactional
    public void dropUser(Integer id) {
        usersDao.deleteUser(id);
    }
}
