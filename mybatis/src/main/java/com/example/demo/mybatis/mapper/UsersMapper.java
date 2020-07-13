package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.pojo.Users;
import com.example.demo.mybatis.pojo.UsersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}