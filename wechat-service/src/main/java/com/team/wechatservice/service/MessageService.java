package com.team.wechatservice.service;



import com.team.wechatservice.config.FeignConfig;
import com.team.wechatservice.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(name = "api", configuration = FeignConfig.class)
public interface MessageService {

    /** 调用api发送消息
     * @author liuzhaohao
     * @date 2021/3/18 12:09 上午
     * @param [messageList]
     * @return boolean
     */
    @RequestMapping(value = "/api/sendWechatMessage", method = RequestMethod.POST)
    boolean sendWechatMessage(@RequestBody List<Message> messageList);

}
