package com.team.fileserver.service;

import com.team.fileserver.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "api", configuration = FeignConfig.class)
public interface FileService {

    @RequestMapping(value = "/api/insertReceiverList", method = RequestMethod.POST)
    boolean insertReceiverList(
            @RequestParam("fileName") String fileName,
            @RequestParam("receiverList") List<String> receiverList);
}
