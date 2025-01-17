package com.team.api.dto;

import com.team.api.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectDto {

    private Project project;

    private List<List<String>> studentList;
}
