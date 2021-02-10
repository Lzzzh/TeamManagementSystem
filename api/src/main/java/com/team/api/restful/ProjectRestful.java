package com.team.api.restful;

import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectRestful {

    @Autowired
    private ProjectService projectService;

    public Result addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }
}
