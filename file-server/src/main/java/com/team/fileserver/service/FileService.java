package com.team.fileserver.service;

import com.team.fileserver.config.FeignConfig;
import com.team.fileserver.entity.Paper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "api", configuration = FeignConfig.class)
public interface FileService {

    /** 插入论文记录
     * @author liuzhaohao
     * @date 2021/3/17 12:07 下午
     * @param [fileName, receiverList]
     * @return boolean
     */

    @RequestMapping(value = "/api/insertReceiverList", method = RequestMethod.POST)
    boolean insertReceiverList(
            @RequestParam("fileName") String fileName,
            @RequestParam("receiverList") List<String> receiverList);

    /** 删除论文记录
     * @author liuzhaohao
     * @date 2021/3/17 12:11 下午
     * @param [paper]
     * @return boolean
     */
    @RequestMapping(value = "/api/deletePaper", method = RequestMethod.POST)
    boolean deletePaper(@RequestBody Paper paper);

    /** 获取用户头像
     * @author liuzhaohao
     * @date 2021/3/17 4:19 下午
     * @param [userId]
     * @return java.lang.String
     */
    @RequestMapping(value = "/api/getUserPhoto", method = RequestMethod.GET)
    String getUserPhoto(@RequestParam(value = "userId") String userId);

    /** 修改/插入用户头像
     * @author liuzhaohao
     * @date 2021/3/17 4:19 下午
     * @param [userId, fileName]
     * @return java.lang.Boolean
     */
    @RequestMapping(value = "/api/saveOrUpdateUserPhoto", method = RequestMethod.GET)
    Boolean saveOrUpdateUserPhoto(@RequestParam(value = "userId") String userId,
                            @RequestParam(value = "fileName") String fileName);


}
