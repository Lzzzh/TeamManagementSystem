package com.team.api.service.impl;

import com.team.api.entity.Result;
import com.team.api.entity.User;
import com.team.api.mapper.UserMapper;
import com.team.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean confirmUser(User user){
        return userMapper.selectOne(user) != null;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) == 1;
    }
}
