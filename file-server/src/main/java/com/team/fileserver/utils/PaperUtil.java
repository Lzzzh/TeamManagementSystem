package com.team.fileserver.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.team.fileserver.entity.Paper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class PaperUtil {

    public static boolean savePaper(MultipartFile file, List<String> receiverList, String paperPath){
        if (file.isEmpty()) {
            return false;
        }
        // 获取文件全名
        String fileName = file.getOriginalFilename();
        log.info("文件路径:" + paperPath);
        // 解决中文问题,liunx 下中文路径,图片显示问题
        //fileName = UUID.randomUUID() + suffixName;

        for (String receiverName: receiverList) {
            byte[] bytes;
            File dir = new File(paperPath + receiverName);
            File paperFile = new File(dir, File.separator + fileName);
            //文件上传-覆盖
            try {
                bytes = file.getBytes();
                // 检测是否存在目录
                if (!paperFile.getParentFile().exists()) {
                    dir.mkdirs();
                }
                FileUtil.writeBytes(bytes, paperFile);
            } catch (Exception e) {
                log.error("文件上传错误");
                return false;
            }
        }
        return true;
    }

    public static void downLoadPaper(String paperPath, Paper paper, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            File file = new File(paperPath + paper.getUserId() + File.separator + paper.getFileName());
            if (!file.exists()) {
                return ;
            }
            // 创建输出流对象
            outputStream = response.getOutputStream();
            //以字节数组的形式读取文件
            byte[] bytes = FileUtil.readBytes(file);
            // 设置返回内容格式
            response.setContentType("application/pdf");
            // 把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码
            // 中文不要太多，最多支持17个中文，因为header有150个字节限制。
            // 这一步一定要在读取文件之后进行，否则文件名会乱码，找不到文件
            String fileName = new String(paper.getFileName().getBytes(StandardCharsets.UTF_8),"ISO8859-1");
            // 设置下载弹窗的文件名和格式（文件名要包括名字和文件格式）
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 返回数据到输出流对象中
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭流对象
            IoUtil.close(outputStream);
        }
    }

    public static boolean deletePaper(String paperPath, Paper paper) {
        String fileName = paperPath + paper.getUserId() + File.separator + paper.getFileName();
        File file = new File(fileName);
        try {
            if (file.exists()) {
                FileUtil.del(file);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
