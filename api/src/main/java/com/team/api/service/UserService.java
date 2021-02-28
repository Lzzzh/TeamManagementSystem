package com.team.api.service;

import com.team.api.dto.UserListDto;
import com.team.api.dto.LoginDto;
import com.team.api.dto.TeacherListDto;
import com.team.api.entity.Result;
import com.team.api.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService{

    Result confirmUser(LoginDto loginDto);

    boolean addUser(User user);

    boolean updateUser(User user);

    List<TeacherListDto> getTeacherList ();

    List<UserListDto> getStudentList();

    List<Map<String, String>> getStudentListByProjectId(String projectId);
}
