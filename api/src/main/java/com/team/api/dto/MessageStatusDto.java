package com.team.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MessageStatusDto {

    private Integer id;

    private String userId;

    private Integer status;
}
