package com.team.api.restful;

import com.team.api.dto.LoginDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;
import com.team.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
class UserRestful {

    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        return userService.confirmUser(loginDto);
    }

//    @CrossOrigin
//    @RequestMapping("/registry")
//    public Result registry(@RequestBody User user) {
//
//    }
}
