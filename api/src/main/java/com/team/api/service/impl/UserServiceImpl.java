package com.team.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.dto.LoginDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;
import com.team.api.mapper.UserMapper;
import com.team.api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result confirmUser(LoginDto loginDto){
        if (StringUtils.isEmpty(loginDto.getUserId())) {
            return new Result(400,"账号不能为空","");
        }
        if (StringUtils.isEmpty(loginDto.getUserPassword())){
            return new Result(400,"密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("USER_ID", loginDto.getUserId());
        User user = userMapper.selectOne(wrapper);
        //比较密码
        if (user != null && user.getUserPassword().equals(loginDto.getUserPassword())){
            LoginDto responseDto = LoginDto.builder()
                    .userId(loginDto.getUserId())
                    .userName(user.getUserName())
                    .userPassword(loginDto.getUserPassword())
                    .authority(user.getAuthority()).build();
            return new Result(200,"",responseDto);
        }
        return new Result(401,"登录失败","");
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) == 1;
    }
}
