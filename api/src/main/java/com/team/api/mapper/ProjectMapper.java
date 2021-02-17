package com.team.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("SELECT p.ID, p.PROJECT_ID, p.PROJECT_NAME, p.PROGRESS, p.TEACHER_NAME, p.CREATE_TIME FROM selection sn, student s, project p WHERE s.STUDENT_ID = #{studentId} AND sn.STUDENT_ID = s.STUDENT_ID AND p.PROJECT_ID = sn.PROJECT_ID")
    List<Project> getProjectListByStudentId(String studentId);
}
