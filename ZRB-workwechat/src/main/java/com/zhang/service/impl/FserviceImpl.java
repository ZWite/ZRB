package com.zhang.service.impl;

import com.zhang.service.Fservice;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FserviceImpl implements Fservice {
    @Override
    public void fileView(String fileUrl, HttpServletResponse response) throws Exception {
        // 读取文件名 例：yyds.jpg
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//        String basePath = "D:\\code\\java\\typoraImgTypeTrans";
        File dir = new File(fileUrl);
        List<File> allFileList = new ArrayList<>();
        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");
            return;
        }
        getAllFile(dir, allFileList);
        for (File file : allFileList) {
//            System.out.println(file.getName());
            try (FileInputStream inputStream = new FileInputStream(file.getPath());
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] data = new byte[1024];
                // 全文件类型（传什么文件返回什么文件流）
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                response.setHeader("Accept-Ranges", "bytes");
                int read;
                while ((read = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, read);
                }
                // 将缓存区数据进行输出
                outputStream.flush();
            } catch (IOException e) {
//            log.error("失败", e);
                throw new Exception("exception");
            }
        }
        System.out.println("该文件夹下共有" + allFileList.size() + "个文件");
    }

    public static void getAllFile(File fileInput, List<File> allFileList) {
        // 获取文件列表
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                getAllFile(file, allFileList);
            } else {
                // 如果是文件则将其加入到文件数组中
                allFileList.add(file);
            }
        }
    }

}
