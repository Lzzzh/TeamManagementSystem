package com.team.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.ProgressDto;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Selection;
import com.team.api.mapper.ProjectMapper;
import com.team.api.mapper.SelectionMapper;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private SelectionMapper selectionMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProject(ProjectDto projectDto) {
        List<Selection> selectionList = new ArrayList<>();
        for (List<String> list: projectDto.getStudentList()) {
            Selection selection = new Selection();
            selection.setStudentId(list.get(1));
            selection.setProjectId(projectDto.getProject().getProjectId());
            selectionList.add(selection);
        }
        return (projectMapper.insert(projectDto.getProject()) > 0)
                && (selectionMapper.insertSelections(selectionList) > 0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProject(Project project) {
        return projectMapper.delete(new QueryWrapper<Project>().eq("PROJECT_ID", project.getProjectId())) > 0
                && selectionMapper.delete(new QueryWrapper<Selection>().eq("PROJECT_ID", project.getProjectId())) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProject(ProjectDto projectDto) {
        List<Selection> selectionList = new ArrayList<>();
        for (List<String> list: projectDto.getStudentList()) {
            Selection selection = new Selection();
            selection.setStudentId(list.get(1));
            selection.setProjectId(projectDto.getProject().getProjectId());
            selectionList.add(selection);
        }
        return projectMapper.update(projectDto.getProject(), new QueryWrapper<Project>().eq("PROJECT_ID", projectDto.getProject().getProjectId())) > 0
                && selectionMapper.delete(new QueryWrapper<Selection>().eq("PROJECT_ID", projectDto.getProject().getProjectId())) > 0
                && selectionMapper.insertSelections(selectionList) > 0;
    }

    @Override
    public IPage<Project> getTeacherProjectList(Page<Project> page, String userId, String searchText) {
        return projectMapper.getProjectListByTeacherId(page, userId, searchText);
    }

    @Override
    public IPage<Project> getStudentProjectList(Page<Project> page, String userId, String searchText) {
        return projectMapper.getProjectListByStudentId(page, userId, searchText);
    }

    @Override
    public List<ProgressDto> getStudentProgress(String userId) {
        return projectMapper.getStudentProgress(userId);
    }

    @Override
    public List<ProgressDto> getTeacherProgress(String userId) {
        return projectMapper.getTeacherProgress(userId);
    }
}
