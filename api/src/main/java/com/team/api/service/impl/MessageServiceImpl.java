package com.team.api.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.api.dto.MessageDto;
import com.team.api.dto.MessageStatusDto;
import com.team.api.entity.Message;
import com.team.api.mapper.MessageMapper;
import com.team.api.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /** 设置消息状态
     * @author liuzhaohao
     * @date 2021/2/28 4:09 下午
     * @param [messageStatusDto]
     * @return boolean
     */
    @Override
    public boolean setMessageStatus(MessageStatusDto messageStatusDto) {
        Message message = new Message();
        message.setStatus(messageStatusDto.getStatus());
        String dateTimeFormat = DateUtil.formatDateTime(messageStatusDto.getCreateTime());
        String messageId = messageStatusDto.getUserId() + "_" + dateTimeFormat;
        return messageMapper.update(message, new QueryWrapper<Message>().eq("MESSAGE_ID", messageId)) > 0;
    }

    @Override
    public List<Message> getMessageList(String userId) {
        return messageMapper.getMessageByUserId(userId);
    }

}
