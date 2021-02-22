package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassDto {

    private String label;

    private String value;

    private List<StudentDto> children;
}
