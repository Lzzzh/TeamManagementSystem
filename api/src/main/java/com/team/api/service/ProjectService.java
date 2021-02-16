package com.team.api.service;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;

import java.util.List;

public interface ProjectService {

    List<Project> getStudentProjectList(String studentId);

    List<Project> getTeacherProjectList(String teacherId);

    boolean addProject(Project project);

    boolean deleteProject(ProjectDto projectDto);

    boolean updateProject(Project project);
}
