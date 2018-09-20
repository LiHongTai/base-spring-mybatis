package com.roger.service;

import com.roger.SpringBaseTestSuit;
import com.roger.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserServiceImpl extends SpringBaseTestSuit {

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user  = new User();
        user.setAge(19);
        user.setPhone("16422455");
        user.setUserName("Mary");
        user.setDesc("Pretty");
        int count = userService.insertUser(user);
        Assert.assertTrue(count >= 0);
    }

}
