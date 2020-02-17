package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.visitorService;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/open")
public class openDoorController {

    @Autowired
    checkinService checkinService;
    @Autowired
    visitorService visitorService;

    @RequestMapping("/door")
    public String opendoor(@RequestParam("face") MultipartFile facefile, @RequestParam("rommId") int roomId) throws IOException {
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);
        File file = new File(filePath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        String result = "0";
        ArrayList<byte[]> Faces1 = checkinService.faceList(roomId);//房客
        ArrayList<byte[]> Face2 = visitorService.getDoorFace(roomId);//访客
        Faces1.addAll(Face2);
        for (byte[] face : Faces1) {
            if (faceRecUtil.getFaceSimilar(file, face) > 0.8) {
                result = "1";
                break;
            }
        }
        return result;
    }

    @RequestMapping("shop")
    public int openshop(@RequestParam("face") MultipartFile facefile) throws IOException {
        int roomNumber = -1;
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);
        File file = new File(filePath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        List<CheckIn> checkIns = checkinService.getAll();
        for (CheckIn checkIn : checkIns) {
            //在未退房的房客中寻找
            if (checkIn.getIsCheckOut() == null) {
                double s = faceRecUtil.getFaceSimilar(file, checkIn.getFace());
                if (s > 0.8) {
                    roomNumber = checkIn.getRoomId();
                    break;
                }
            }
        }
        return roomNumber;
    }

}
