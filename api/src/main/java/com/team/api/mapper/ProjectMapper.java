package com.team.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.ProgressDto;
import com.team.api.dto.StudentListDto;
import com.team.api.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    IPage<Project> getProjectListByTeacherId(Page<?> page, String userId, String searchText);

    IPage<Project> getProjectListByStudentId(Page<?> page, String userId, String searchText);

    List<ProgressDto> getStudentProgress(String userId);

    List<ProgressDto> getTeacherProgress(String userId);

}
