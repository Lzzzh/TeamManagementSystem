package com.team.api.controller;

import com.team.api.dto.UserListDto;
import com.team.api.dto.LoginDto;
import com.team.api.dto.TeacherListDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;
import com.team.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginDto loginDto) {
        return userService.confirmUser(loginDto);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Result updateUser(@RequestBody User user) {
        boolean res = userService.updateUser(user);
        if (res) {
            return Result.success("修改成功", user);
        }else {
            return Result.fail("修改失败", "");
        }
    }

    @ApiOperation("查询项目相关的学生列表")
    @RequestMapping(value = "/studentListByProject", method = RequestMethod.GET)
    public Result studentProjectList(@RequestParam(value = "projectId") String projectId){
        List<Map<String, String>> studentProjectList = userService.getStudentListByProjectId(projectId);
        return Result.success("查询成功！", studentProjectList);
    }

    @ApiOperation("查询学生列表")
    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    public Result studentList(){
        List<UserListDto> userList = userService.getStudentList();
        return Result.success("查询成功！", userList);
    }

    @ApiOperation("查询所有用户列表(包括老师)")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public Result getUserList() {
        List<UserListDto> userList = userService.getStudentList();
        List<TeacherListDto> teacherList = userService.getTeacherList();
        userList.add(new UserListDto("老师", "老师", teacherList));
        if (!userList.isEmpty()) {
            return Result.success("查询成功！", userList);
        }else {
            return Result.fail("查询失败！", "");
        }
    }

    // TODO: 2021/2/24 用户头像上传 
//    Result userPhoto();

    // TODO: 2021/2/24 注册功能
//    @CrossOrigin
//    @RequestMapping("/registry")
//    public Result registry(@RequestBody User user) {
//
//    }
}
