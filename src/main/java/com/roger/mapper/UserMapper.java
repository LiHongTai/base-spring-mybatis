package com.roger.mapper;

import com.roger.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    int insertUser(User user);
}
