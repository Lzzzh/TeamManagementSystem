package com.team.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.PageDto;
import com.team.api.dto.PaperDto;
import com.team.api.entity.Paper;
import com.team.api.entity.Result;
import com.team.api.service.PaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author liuzhaohao
 * @date 2021/3/16 11:01 上午
 * @param 
 * @return 
 */
@RestController
@RequestMapping("/api")
public class PaperController {
    
    @Autowired
    private PaperService paperService;

    @ApiOperation("插入论文分享记录")
    @RequestMapping(value = "/insertReceiverList", method = RequestMethod.POST)
    public Boolean insertReceiverList(
            @RequestParam("fileName") String fileName,
            @RequestParam("receiverList") List<String> receiverList) {
        List<Paper> paperList = new ArrayList<>();
        for (String userId: receiverList) {
            paperList.add(new Paper(userId, fileName));
        }
        return paperService.saveBatch(paperList);
    }

    @ApiOperation("查询用户的论文")
    @RequestMapping(value = "/getPaperList", method = RequestMethod.POST)
    public Result<?> getPaperList(@RequestBody PaperDto paperDto) {
        IPage<Paper> paperList =  paperService.page(
                new Page<>(paperDto.getPageIndex(), paperDto.getPageSize()),
                new QueryWrapper<Paper>().eq("USER_ID", paperDto.getUserId()));
        return Result.success("查询成功！", paperList);
    }


}

