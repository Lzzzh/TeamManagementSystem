package com.team.api.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.dto.UserListDto;
import com.team.api.dto.StudentListDto;
import com.team.api.dto.LoginDto;
import com.team.api.dto.TeacherListDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;
import com.team.api.mapper.SelectionMapper;
import com.team.api.mapper.UserMapper;
import com.team.api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SelectionMapper selectionMapper;

    @Override
    public Result confirmUser(LoginDto loginDto){
        if (StringUtils.isEmpty(loginDto.getUserId())) {
            return new Result(HttpStatus.HTTP_BAD_REQUEST,"账号不能为空","");
        }
        if (StringUtils.isEmpty(loginDto.getUserPassword())){
            return new Result(HttpStatus.HTTP_BAD_REQUEST,"密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("USER_ID", loginDto.getUserId());
        User user = userMapper.selectOne(wrapper);
        //比较密码
        if (user != null && user.getUserPassword().equals(loginDto.getUserPassword())){
            userMapper.setLastLoginTime(DateUtil.parse(DateUtil.today()), loginDto.getUserId());
            LoginDto responseDto = LoginDto.builder()
                    .userId(loginDto.getUserId())
                    .userName(user.getUserName())
                    .userPassword(loginDto.getUserPassword())
                    .authority(user.getAuthority())
                    .lastLoginTime(user.getLastLoginTime()).build();
            return new Result(HttpStatus.HTTP_OK,"",responseDto);
        }
        return new Result(HttpStatus.HTTP_BAD_REQUEST,"登录失败","");
    }

    /** 修改用户信息
     * @author liuzhaohao
     * @date 2021/3/1 5:35 下午
     * @param [user]
     * @return boolean
     */
    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user, new QueryWrapper<User>().eq("USER_ID", user.getUserId())) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public List<UserListDto> getStudentList() {
        List<UserListDto> userList = new ArrayList<>();
        List<String> classList = userMapper.getClassList();
        for (String className: classList) {
            List<StudentListDto> studentList = userMapper.getStudentListByClass(className);
            userList.add(new UserListDto(className, className, studentList));
        }
        return userList;
    }

    /** 查询该项目下的学生列表
     * @author liuzhaohao
     * @date 2021/3/1 5:35 下午
     * @param [projectId]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    @Override
    public List<Map<String, String>> getStudentListByProjectId(String projectId) {
        return selectionMapper.getStudentListByProjectId(projectId);
    }

    /** 查询教师列表
     * @author liuzhaohao
     * @date 2021/3/1 5:34 下午
     * @param []
     * @return java.util.List<com.team.api.dto.TeacherListDto>
     */
    @Override
    public List<TeacherListDto> getTeacherList() {
        return userMapper.getTeacherList();
    }
}
