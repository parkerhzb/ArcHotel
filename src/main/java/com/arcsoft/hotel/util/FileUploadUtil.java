package com.arcsoft.hotel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public String fileUpload(MultipartFile file, String OriginalPath) throws IllegalStateException, IOException {
        if (file.getSize() == 0) {
            return null;
        }

        String path = OriginalPath + File.separator + "upload";
        // 如果目录不存在则创建
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        path += File.separator + file.getOriginalFilename();
        System.out.println(path);
        File newFile = new File(path);
        file.transferTo(newFile);
        return path;
    }

    public String fileUpload(MultipartFile file, String OriginalPath, String pack, String name) throws IllegalStateException, IOException {
        if (file.getSize() == 0) {
            return null;
        }

        String path = OriginalPath + File.separator + "upload" + File.separator + pack;
        // 如果目录不存在则创建
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        path += File.separator + name;
        System.out.println(path);
        File newFile = new File(path);
        file.transferTo(newFile);
        return path;
    }
}
