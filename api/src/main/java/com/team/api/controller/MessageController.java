package com.team.api.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.dto.MessageDto;
import com.team.api.dto.MessageStatusDto;
import com.team.api.entity.Message;
import com.team.api.entity.Result;
import com.team.api.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("发送消息")
    @RequestMapping("/sendMessage")
    public Result sendMessage(@RequestBody MessageDto messageDto) {
        if (!StringUtils.isEmpty(messageDto.getContent())) {
            Date createTime = DateUtil.date();
            List<Message> messageList = new ArrayList<>();
            for (List<String> userIdMap : messageDto.getReceiverList()) {
                Message message = new Message();
                String messageId = userIdMap.get(1) + "_" + DateUtil.now();
                message.setMessageId(messageId);
                message.setReceiverId(userIdMap.get(1));
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

    @ApiOperation("获取消息")
    @RequestMapping("/getMessageList")
    public Result getMessageList(@RequestParam String userId) {
        List<Message> messageList = messageService.getMessageList(userId);
        return Result.success("查询成功！", messageList);
    }

    @ApiOperation("修改消息状态")
    @RequestMapping("/setMessageStatus")
    public Result setMessageStatus(@RequestBody MessageStatusDto messageStatusDto) {
        boolean res = messageService.setMessageStatus(messageStatusDto);
        if (res) {
            return Result.success("修改成功！", messageStatusDto);
        }else {
            return Result.fail("修改失败！", "");
        }
    }

    @ApiOperation("删除消息")
    @RequestMapping("/deleteMessage")
    public Result deleteMessage(@RequestBody MessageStatusDto messageStatusDto) {
        String dateTimeFormat = DateUtil.formatDateTime(messageStatusDto.getCreateTime());
        String messageId = messageStatusDto.getUserId() + "_" + dateTimeFormat;
        boolean res = messageService.remove(new QueryWrapper<Message>().eq("MESSAGE_ID", messageId));
        if (res) {
            return Result.success("删除成功！", messageStatusDto);
        }else {
            return Result.fail("删除失败！", "");
        }
    }
}
