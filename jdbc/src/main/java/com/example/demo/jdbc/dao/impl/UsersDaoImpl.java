package com.example.demo.jdbc.dao.impl;

import com.example.demo.jdbc.dao.UsersDao;
import com.example.demo.jdbc.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 持久层
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUsers(Users users) {
        String sql = "insert into users(username,usersex) values(?,?)";
        this.jdbcTemplate.update(sql, users.getUsername(), users.getUsersex());
    }

    @Override
    public List<Users> selectUserAll() {
        String sql = "select * from users";

        // 结果集的映射
        RowMapper<Users> rowMapper = new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users users = new Users();
                users.setUserid(resultSet.getInt("userid"));
                users.setUsername(resultSet.getString("username"));
                users.setUsersex(resultSet.getString("usersex"));
                return users;
            }
        };

        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Users selectUserById(Integer id) {
        String sql = "select * from users where userid = ?";
        Users users = new Users();

        // 数组位置与sql语句中问号绑定
        Object[] arr = new Object[]{id};

        RowCallbackHandler handler = new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                users.setUserid(resultSet.getInt("userid"));
                users.setUsersex(resultSet.getString("username"));
                users.setUsername(resultSet.getString("usersex"));
            }
        };
        this.jdbcTemplate.query(sql, arr, handler);
        return users;
    }

    @Override
    public void updateUser(Users users) {
        String sql = "update users set username = ?,usersex = ? where userid = ?";
        this.jdbcTemplate.update(sql, users.getUsername(), users.getUsersex(), users.getUserid());
    }

    @Override
    public void deleteUser(Integer id) {
        String sql = "delete from users where userid = ?";
        this.jdbcTemplate.update(sql, id);
    }
}
