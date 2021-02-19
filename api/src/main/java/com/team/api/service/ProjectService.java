package com.team.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;

import java.util.List;

public interface ProjectService {

    IPage<Project> getStudentProjectList(Page<Project> page, String userId, String searchText);

    IPage<Project> getTeacherProjectList(Page<Project> page, String userId, String searchText);

    boolean addProject(Project project);

    boolean deleteProject(Project project);

    boolean updateProject(Project project);
}
