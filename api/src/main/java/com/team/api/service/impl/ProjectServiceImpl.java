package com.team.api.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.mapper.ProjectInfoMapper;
import com.team.api.mapper.ProjectMapper;
import com.team.api.service.ProjectService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProject(Project project) {
        return projectMapper.insert(project) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProject(Project project) {
        return (projectMapper.delete(new QueryWrapper<Project>().eq("PROJECT_ID", project.getProjectId())) > 0)
                && (projectInfoMapper.delete(new QueryWrapper<ProjectDto>().eq("PROJECT_ID", project.getProjectId())) > 0);
    }

    @Override
    public Result updateProject(Project project) {
        return null;
    }

    @Override
    public List<Project> getProjectList(String userId) {
        return projectMapper.getAllProjectByUserId(userId);
    }
}
