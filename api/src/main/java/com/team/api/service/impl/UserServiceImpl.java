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
import com.team.api.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SelectionMapper selectionMapper;

    @Override
    public Result confirmUser(LoginDto loginDto){
        if (StringUtils.isEmpty(loginDto.getUserId())) {
            return Result.fail("账号不能为空", "");
        }
        if (StringUtils.isEmpty(loginDto.getUserPassword())){
            return Result.fail("密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID", loginDto.getUserId());
        User user = userMapper.selectOne(wrapper);
        //比较密码
        if (user != null && user.getUserPassword().equals(loginDto.getUserPassword())){
            userMapper.setLastLoginTime(DateUtil.parse(DateUtil.today()), loginDto.getUserId());
            LoginDto responseDto = LoginDto.builder()
                    .userId(loginDto.getUserId())
                    .userName(user.getUserName())
                    .userPassword(loginDto.getUserPassword())
                    .token(TokenUtil.sign(user))
                    .authority(user.getAuthority())
                    .lastLoginTime(user.getLastLoginTime()).build();
            return Result.success("登录成功！", responseDto);
        }
        log.error("登录校验失败");
        return Result.fail("登录失败","");
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
        List<Map> mapList = userMapper.getTeacherList();
        List<TeacherListDto> teacherList = new ArrayList<>();
        for (Map map: mapList) {
            teacherList.add(new TeacherListDto(map.get("TEACHER_NAME").toString(), map.get("TEACHER_ID").toString(), "老师"));
        }
        return teacherList;
    }
}
