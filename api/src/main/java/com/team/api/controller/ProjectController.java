package com.team.api.controller;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/addProject")
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
            return Result.fail("删除失败！", "");
        }
    }

    @RequestMapping(value = "/projectList", method = RequestMethod.GET)
    public List<Project> list(@RequestParam(value = "userId") String userId) {
        return projectService.getProjectList(userId);
    }
}
