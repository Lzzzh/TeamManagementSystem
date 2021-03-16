package com.team.wechatservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.wechatservice.entity.Message;
import com.team.wechatservice.mapper.MessageMapper;
import com.team.wechatservice.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
