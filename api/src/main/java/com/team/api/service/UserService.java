package com.team.api.service;

import com.team.api.entity.Result;
import com.team.api.entity.User;

public interface UserService {

    boolean confirmUser(User user);

    boolean addUser(User user);
}
