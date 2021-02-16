package com.team.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectInfoMapper extends BaseMapper<ProjectDto> {

    @Select("SELECT p.PROJECT_ID, p.PROJECT_NAME, p.TEACHER_NAME, P.PROGRESS" +
            "FROM choose c, student s, project p, user u " +
            "WHERE u.USER_ID = #{userId} AND s.STUDENT_ID = u.USER_ID" +
            "AND c.STUDENT_ID = s.STUDENT_ID AND AND p.PROJECT_ID = c.PROJECT_ID")
    List<Project> getAllProjectByUserId(ProjectDto projectDto);
}
