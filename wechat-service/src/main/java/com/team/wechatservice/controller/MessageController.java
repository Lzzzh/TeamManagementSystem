package com.team.wechatservice.controller;

import cn.hutool.core.date.DateUtil;
import com.team.wechatservice.dto.MessageDto;
import com.team.wechatservice.entity.Message;
import com.team.wechatservice.entity.Result;
import com.team.wechatservice.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wechat-service")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("发送消息")
    @RequestMapping("/sendMessage")
    public Result<?> sendMessage(@RequestBody MessageDto messageDto) {
        if (!StringUtils.isEmpty(messageDto.getContent())) {
            Date createTime = DateUtil.date();
            List<Message> messageList = new ArrayList<>();
            for (String receiverId : messageDto.getReceiverList()) {
                Message message = new Message();
                String messageId = receiverId + "_" + DateUtil.now();
                message.setMessageId(messageId);
                message.setReceiverId(receiverId);
                message.setSenderId(messageDto.getSenderId());
                message.setContent(messageDto.getContent());
                message.setCreateTime(createTime);
                // 未读状态
                message.setStatus(0);
                messageList.add(message);
            }
            boolean res = messageService.saveBatch(messageList);
            if (res) {
                return Result.success("发送消息成功！", messageDto);
            }else {
                return Result.fail("发送失败！", "");
            }
        }else {
            return Result.fail("消息内容为空！", "");
        }
    }

}