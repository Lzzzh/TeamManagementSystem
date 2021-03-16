package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaperDto {

    private String userId;

    private Integer pageIndex;

    private Integer pageSize;
}
