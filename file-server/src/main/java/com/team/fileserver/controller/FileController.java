package com.team.fileserver.controller;

import cn.hutool.core.io.FileUtil;
import com.team.fileserver.entity.Paper;
import com.team.fileserver.entity.Result;
import com.team.fileserver.service.FileService;
import com.team.fileserver.utils.PaperUtil;
import com.team.fileserver.utils.PhotoUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${paper_path}")
    private String paperPath;

    @Value("${photo_path}")
    private String photoPath;

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

    @ApiOperation("下载/预览论文")
    @RequestMapping(value = "/downloadPaper", method = RequestMethod.POST)
    public void downLoadPaper(@RequestBody Paper paper,
                                    HttpServletResponse response) {
        PaperUtil.downLoadPaper(paperPath, paper, response);
    }

    @ApiOperation("删除论文")
    @RequestMapping(value = "/deletePaper", method = RequestMethod.POST)
    public Result<?> deletePaper(@RequestBody Paper paper) {
        if (PaperUtil.deletePaper(paperPath, paper) && fileService.deletePaper(paper)) {
            return Result.success("删除成功！", paper.getFileName());
        }else {
            return Result.fail("删除失败！", "");
        }
    }

    @ApiOperation("获取用户头像")
    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public void getUserPhoto(@RequestParam("userId") String userId,
                             HttpServletResponse response) {
        String fileName = fileService.getUserPhoto(userId);
        PhotoUtil.downloadUserPhoto(photoPath, userId, fileName, response);
    }

    @ApiOperation("上传用户头像")
    @RequestMapping(value = "/uploadUserPhoto", method = RequestMethod.POST)
    public Result<?> uploadUserPhoto(@RequestParam(value = "userId") String userId,
            @RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("文件为空！", "");
        }
        // 获取文件全名
        String fileName = file.getOriginalFilename();
        // 解决中文问题,liunx 下中文路径,图片显示问题
        byte[] bytes;
        File dir = new File(photoPath + userId);
        File photoFile = new File(dir, File.separator + fileName);
        //文件上传-覆盖
        boolean res;
        try {
            bytes = file.getBytes();
            // 检测是否存在目录
            if (!photoFile.getParentFile().exists()) {
                dir.mkdirs();
            }
            if (photoFile.exists()) {
                FileUtil.del(photoFile);
                FileUtil.writeBytes(bytes, photoFile);
            }else {
                FileUtil.writeBytes(bytes, photoFile);
            }
            res = fileService.saveOrUpdateUserPhoto(userId, file.getOriginalFilename());
        } catch (Exception e) {
            log.error("文件上传错误");
            return Result.fail("上传失败！", "");
        }
        return res ? Result.success("上传成功！", file.getOriginalFilename()) :
                Result.fail("上传失败！", "");
    }

}
