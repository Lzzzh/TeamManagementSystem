package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDto {

    private String userId;

    private String projectId;

    private String projectName;

    private String teacherName;

    private Integer progress;
}
