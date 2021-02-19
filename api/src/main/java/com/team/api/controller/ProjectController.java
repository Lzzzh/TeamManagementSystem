package com.team.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation("新增项目")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public Result addProject(@RequestBody Project project) {
        try {
            boolean res = projectService.addProject(project);
            if (res) {
                return Result.success("新增成功！", project);
            }else {
                return Result.fail("新增失败!", "");
            }
        }catch (Exception e) {
            return Result.fail("新增失败!", "");
        }
    }

    @ApiOperation("删除项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public Result deleteProject(@RequestBody Project project) {
        try {
            boolean res = projectService.deleteProject(project);
            if (res) {
                return Result.success("删除成功！", project);
            }else {
                return Result.fail("删除失败！", "");
            }
        }catch (Exception e) {
            log.error("删除失败");
            return Result.fail("删除失败！", "");
        }
    }

    @ApiOperation("修改项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public Result updateProject(@RequestBody Project project) {
        boolean res = projectService.updateProject(project);
        if (res) {
            return Result.success("更新成功！", project);
        }else {
            return Result.fail("更新失败！", project);
        }
    }

    @ApiOperation("学生端查询项目列表")
    @RequestMapping(value = "/studentProjectList", method = RequestMethod.POST)
    public Result studentProjectList(@RequestBody ProjectDto projectDto) {
        if (!StringUtils.isEmpty(projectDto.getSearchText())) {
             projectDto.setSearchText(projectDto.getSearchText().trim());
        }
        IPage<Project> projectList = projectService.getStudentProjectList(
                new Page<>(projectDto.getPageIndex(), projectDto.getPageSize()),
                projectDto.getUserId(), projectDto.getSearchText());
        return Result.success("查询成功！", projectList);
    }

    @ApiOperation("教师端查询项目列表")
    @RequestMapping(value = "/teacherProjectList", method = RequestMethod.POST)
    public Result teacherProjectList(@RequestBody ProjectDto projectDto) {
        if (!StringUtils.isEmpty(projectDto.getSearchText())) {
            projectDto.setSearchText(projectDto.getSearchText().trim());
        }
        IPage<Project> projectList = projectService.getTeacherProjectList(
                new Page<>(projectDto.getPageIndex(), projectDto.getPageSize()),
                projectDto.getUserId(), projectDto.getSearchText());
        return Result.success("查询成功！", projectList);
    }
}
