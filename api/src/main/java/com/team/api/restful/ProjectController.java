package com.team.api.restful;

import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public Result addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    public Result list(@RequestBody ProjectDto projectDto) {
        return projectService.getProjectList(projectDto);
    }
}
