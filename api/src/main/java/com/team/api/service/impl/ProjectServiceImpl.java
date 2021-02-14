package com.team.api.service.impl;

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

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public Result addProject(Project project) {
        return projectMapper.insert(project) == 1
                ? new Result(200, "新增成功！", project)
                : new Result(400, "新增失败！", "");
    }

    @Override
    public Result getProjectList(ProjectDto projectDto) {
        return new Result(200, "", "");
    }
}
