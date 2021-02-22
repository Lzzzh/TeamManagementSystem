package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto {

    private String userId;

    private String searchText;

    private Integer pageIndex;

    private Integer pageSize;
}
