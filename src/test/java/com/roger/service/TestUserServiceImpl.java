package com.roger.service;

import com.roger.SpringBaseTestSuit;
import com.roger.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserServiceImpl extends SpringBaseTestSuit {

    @Autowired(required = false)
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user  = new User();
        user.setAge(14);
        user.setPhone("15945678956");
        user.setUserName("Lily");
        int count = userService.insertUser(user);
        Assert.assertTrue(count >= 0);
    }

}
