package com.roger.service.impl;

import com.roger.entity.User;
import com.roger.mapper.UserMapper;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Transactional
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
