package com.team.fileserver.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.team.fileserver.entity.Paper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PhotoUtil {

    public static void downloadUserPhoto(String paperPath, String userId, String fileName, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            File file = new File(paperPath + userId + File.separator + fileName);
            if (StringUtils.isEmpty(fileName) || !file.exists()) {
                outputStream = response.getOutputStream();
                outputStream.write(new byte[0]);
            }else {
                // 创建输出流对象
                outputStream = response.getOutputStream();
                //以字节数组的形式读取文件
                byte[] bytes = FileUtil.readBytes(file);
                // 设置返回内容格式
                response.setContentType("application/image");
                // 把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码
                // 中文不要太多，最多支持17个中文，因为header有150个字节限制。
                // 这一步一定要在读取文件之后进行，否则文件名会乱码，找不到文件
                String image = new String(fileName.getBytes(StandardCharsets.UTF_8),"ISO8859-1");
                // 设置下载弹窗的文件名和格式（文件名要包括名字和文件格式）
                response.setHeader("Content-Disposition", "attachment;filename=" + image);
                // 返回数据到输出流对象中
                outputStream.write(bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭流对象
            IoUtil.close(outputStream);
        }
    }

}
