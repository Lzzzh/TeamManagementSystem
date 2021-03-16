package com.team.fileserver.controller;

import com.team.fileserver.entity.Paper;
import com.team.fileserver.entity.Result;
import com.team.fileserver.service.FileService;
import com.team.fileserver.utils.PaperUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${paper_path}")
    private String paperPath;

    @Autowired
    private FileService fileService;

    @ApiOperation("上传论文")
    @RequestMapping(value = "/sharePaper", method = RequestMethod.POST)
    public Result<?> sharePaper(@RequestParam(value = "receiverList") List<String> receiverList,
                               @RequestParam(value = "file") MultipartFile file) {
        if (!receiverList.isEmpty() && !file.isEmpty()) {
            if (PaperUtil.savePaper(file, receiverList, paperPath) &&
                fileService.insertReceiverList(file.getOriginalFilename(), receiverList)){
                return Result.success("上传成功！", file.getOriginalFilename());
            }else {
                return Result.fail("上传失败！", "");
            }
        }else if (receiverList.isEmpty()) {
            return Result.fail("接收人为空！", "");
        }else {
            return Result.fail("文件为空！", "");
        }
    }

    @ApiOperation("下载论文")
    @RequestMapping(value = "/downloadPaper", method = RequestMethod.POST)
    public void downLoadPapaer(@RequestBody Paper paper,
                                    HttpServletResponse response) {
        PaperUtil.downLoadPaper(paperPath, paper, response);
    }
}
