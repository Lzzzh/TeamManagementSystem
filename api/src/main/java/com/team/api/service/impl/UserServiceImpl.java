package com.team.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public User confirmUser(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID", user.getUserId());
        queryWrapper.eq("USER_PASSWORD", user.getUserPassword());
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) == 1;
    }
}
