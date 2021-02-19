package com.team.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public boolean deleteProject(Project project) {
        return (projectMapper.delete(new QueryWrapper<Project>().eq("PROJECT_ID", project.getProjectId())) > 0)
                && (selectionMapper.delete(new QueryWrapper<Selection>().eq("PROJECT_ID", project.getProjectId())) > 0);
    }

    @Override
    public boolean updateProject(Project project) {
        return projectMapper.update(project, new QueryWrapper<Project>().eq("PROJECT_ID", project.getProjectId())) > 0;
    }

    @Override
    public IPage<Project> getTeacherProjectList(Page<Project> page, String userId, String searchText) {
        return projectMapper.getProjectListByTeacherId(page, userId, searchText);
    }

    @Override
    public IPage<Project> getStudentProjectList(Page<Project> page, String userId, String searchText) {
        return projectMapper.getProjectListByStudentId(page, userId, searchText);
    }
}
