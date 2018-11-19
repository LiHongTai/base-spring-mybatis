package com.roger.mapper;

import com.roger.SpringBaseTestSuit;
import com.roger.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestUserMapper extends SpringBaseTestSuit {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testGetUserList(){
       List<User> users = userMapper.getUserList();
       Assert.assertTrue(users.size() >= 0);
    }

    @Test
    public void testInsertUser(){
        User user  = new User();
        user.setAge(19);
        user.setPhone("15618494678");
        user.setUserName("Mary");
        userMapper.insertUser(user);
    }
}
