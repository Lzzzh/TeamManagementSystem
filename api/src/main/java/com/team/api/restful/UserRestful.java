package com.team.api.restful;

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
@CrossOrigin
class UserRestful {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.confirmUser(user)
                ? new Result(200, "登录成功！", user)
                : new Result(400, "用户名或密码错误！", "");
    }

    @RequestMapping("/registry")
    public Result registry(@RequestBody User user) {
        if (!userService.confirmUser(user)) {
            userService.addUser(user);
            return new Result(200, "注册成功！", user);
        }else {
            return new Result(400, "注册失败！该用户已存在！", "");
        }
    }
}
