package com.team.api.service;

import com.team.api.entity.Result;
import com.team.api.entity.User;

public interface UserService {

    User confirmUser(User user);

    boolean addUser(User user);
}
