package com.team.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.entity.Photo;
import com.team.api.service.PhotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @ApiOperation("获取用户头像")
    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public String getUserPhoto(@RequestParam(value = "userId") String userId) {
        return photoService.getOne(new QueryWrapper<Photo>().eq("USER_ID", userId)).getFileName();
    }

    @ApiOperation("修改用户头像")
    @RequestMapping(value = "/updateUserPhoto", method = RequestMethod.GET)
    public Boolean updateUserPhoto(@RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "fileName") String fileName) {
        return photoService.update(new Photo(userId, fileName), new QueryWrapper<Photo>().eq("USER_ID", userId));
    }

    @ApiOperation("插入用户头像记录")
    @RequestMapping(value = "/insertUserPhoto", method = RequestMethod.GET)
    public Boolean insertUserPhoto(@RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "fileName") String fileName) {
        return photoService.save(new Photo(userId, fileName));
    }
}
