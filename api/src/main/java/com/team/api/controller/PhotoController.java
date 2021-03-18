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

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @ApiOperation("获取用户头像")
    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public String getUserPhoto(@RequestParam(value = "userId") String userId) {
        Photo photo = photoService.getOne(new QueryWrapper<Photo>().eq("USER_ID", userId));
        if (photo == null) {
            return "";
        }else {
            return photo.getFileName();
        }
    }

    @ApiOperation("修改/插入用户头像")
    @RequestMapping(value = "/saveOrUpdateUserPhoto", method = RequestMethod.GET)
    public Boolean updateUserPhoto(@RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "fileName") String fileName) {
        return photoService.saveOrUpdate(new Photo(userId, fileName), new QueryWrapper<Photo>().eq("USER_ID", userId));
    }

}
