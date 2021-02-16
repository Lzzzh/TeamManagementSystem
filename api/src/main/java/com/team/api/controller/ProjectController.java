package com.team.api.controller;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public Result deleteProject(@RequestBody ProjectDto projectDto) {
        try {
            boolean res = projectService.deleteProject(projectDto);
            if (res) {
                return Result.success("删除成功！", projectDto);
            }else {
                return Result.fail("删除失败！", "");
            }
        }catch (Exception e) {
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
    @RequestMapping(value = "/studentProjectList", method = RequestMethod.GET)
    public Result studentProjectList(@RequestParam(value = "studentId") String studentId) {
        List<Project> projectList = projectService.getStudentProjectList(studentId);
        if (!projectList.isEmpty()) {
            return Result.success("查询成功！", projectList);
        }else {
            return Result.fail("查询失败！", "");
        }
    }

    @ApiOperation("教师端查询项目列表")
    @RequestMapping(value = "/teacherProjectList", method = RequestMethod.GET)
    public Result teacherProjectList(@RequestParam(value = "teacherId") String teacherId) {
        List<Project> projectList = projectService.getTeacherProjectList(teacherId);
        if (!projectList.isEmpty()) {
            return Result.success("查询成功！", projectList);
        }else {
            return Result.fail("查询失败！", "");
        }
    }
}
