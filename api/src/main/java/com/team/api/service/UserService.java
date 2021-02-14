package com.team.api.service;

import com.team.api.dto.LoginDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;

public interface UserService {

    Result confirmUser(LoginDto loginDto);

    boolean addUser(User user);
}
