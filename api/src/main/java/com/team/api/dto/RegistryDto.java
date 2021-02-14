package com.team.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryDto {

    private String userId;

    private String userName;

    private String userPassword;
}
