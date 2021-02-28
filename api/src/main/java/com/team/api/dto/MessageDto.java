package com.team.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
public class MessageDto {

    private List<List<String>> receiverList;

    private String content;

    private String senderId;
}
