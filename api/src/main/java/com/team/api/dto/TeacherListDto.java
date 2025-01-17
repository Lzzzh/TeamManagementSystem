package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherListDto {

    private String label;

    private String value;

    private String className;
}
