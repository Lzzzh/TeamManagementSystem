package com.team.wechatservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageDto {

    private String senderId;

    private String content;

    private List<String> receiverList;
}
