package com.team.api.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.team.api.dto.MessageDto;
import com.team.api.dto.MessageStatusDto;
import com.team.api.entity.Message;

import java.util.List;

public interface MessageService extends IService<Message> {

    boolean setMessageStatus(MessageStatusDto messageStatusDto);

    List<Message> getMessageList(String userid);

}
