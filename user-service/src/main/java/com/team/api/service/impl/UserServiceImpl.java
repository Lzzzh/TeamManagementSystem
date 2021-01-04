package com.team.api.service.impl;

import com.team.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean getRegistStatus(String userId){
        return false;
    }
}
