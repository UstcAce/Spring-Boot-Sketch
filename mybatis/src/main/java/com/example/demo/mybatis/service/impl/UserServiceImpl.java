package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.mapper.UsersMapper;
import com.example.demo.mybatis.pojo.Users;
import com.example.demo.mybatis.pojo.UsersExample;
import com.example.demo.mybatis.service.UsersService;
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
    private UsersMapper usersMapper;

    @Override
    @Transactional
    public void addUser(Users users) {
        this.usersMapper.insert(users);
    }

    @Override
    public List<Users> findAllUsers() {
        UsersExample example = new UsersExample();
        return this.usersMapper.selectByExample(example);
    }

    @Override
    public Users findUserById(Integer id) {
        return this.usersMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void modifyUser(Users users) {
        UsersExample example = new UsersExample();
        this.usersMapper.updateByExample(users, example);
    }

    @Override
    @Transactional
    public void dropUser(Integer id) {
        this.usersMapper.deleteByPrimaryKey(id);
    }
}
