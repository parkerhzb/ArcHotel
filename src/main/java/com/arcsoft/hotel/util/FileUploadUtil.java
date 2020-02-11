package com.arcsoft.hotel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public String fileUpload(MultipartFile file, String OriginalPath) throws IllegalStateException, IOException {
        if (file.getSize() == 0) {
            return null;
        }
//        System.err.println("文件是否为空 ： " + file.isEmpty());
//        System.err.println("文件的大小为 ：" + file.getSize());
//        System.err.println("文件的媒体类型为 ： " + file.getContentType());
//        System.err.println("文件的名字： " + file.getName());
//        System.err.println("文件的originName为： " + file.getOriginalFilename());

        String path = OriginalPath + File.separator + "upload";
        // 如果目录不存在则创建
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        path += File.separator + file.getOriginalFilename();
        System.out.println(path);
        File newFile = new File(path);
        file.transferTo(newFile);
        return path;
    }
}
