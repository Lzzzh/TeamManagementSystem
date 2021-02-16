package com.team.api.service;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList(String userId);

    boolean addProject(Project project);

    boolean deleteProject(Project project);

    Result updateProject(Project project);
}
