package com.team.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.entity.Selection;
import com.team.api.mapper.ProjectMapper;
import com.team.api.mapper.SelectionMapper;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private SelectionMapper selectionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProject(Project project) {
        project.setCreateTime(new Date(System.currentTimeMillis()));
        return projectMapper.insert(project) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProject(ProjectDto projectDto) {
        return (projectMapper.delete(new QueryWrapper<Project>().eq("PROJECT_ID", projectDto.getProjectId())) > 0)
                && (selectionMapper.delete(new QueryWrapper<Selection>().eq("PROJECT_ID", projectDto.getProjectId())) > 0);
    }

    @Override
    public boolean updateProject(Project project) {
        return projectMapper.update(project, new QueryWrapper<Project>().eq("PROJECT_ID", project.getProjectId())) > 0;
    }

    @Override
    public List<Project> getStudentProjectList(String studentId) {
        return projectMapper.getProjectListByStudentId(studentId);
    }

    @Override
    public List<Project> getTeacherProjectList(String teacherId) {
        return projectMapper.selectList(new QueryWrapper<Project>().eq("TEACHER_ID", teacherId));
    }
}
