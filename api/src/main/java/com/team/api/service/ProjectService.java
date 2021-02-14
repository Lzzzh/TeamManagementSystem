package com.team.api.service;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;

public interface ProjectService {

    Result addProject(Project project);

    Result getProjectList(ProjectDto projectDto);
}
