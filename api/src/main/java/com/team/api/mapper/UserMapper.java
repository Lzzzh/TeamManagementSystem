package com.team.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.api.dto.StudentListDto;
import com.team.api.dto.TeacherListDto;
import com.team.api.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User>{
    List<StudentListDto> getUserList();

    List<String> getClassList();

    List<StudentListDto> getStudentListByClass(String className);

    List<Map<String, String>> getTeacherList();

    boolean setLastLoginTime(Date lastLoginTime, String userId);
}
