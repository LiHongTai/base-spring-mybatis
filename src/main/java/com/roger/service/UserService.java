package com.roger.service;

import com.roger.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    int insertUser(User user);
}
