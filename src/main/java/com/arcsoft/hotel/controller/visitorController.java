package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.service.faceService;
import com.arcsoft.hotel.service.impl.faceServiceImpl;
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

@RestController
public class visitorController {
    @Autowired
    faceService faceService;
    @Autowired
    visitorService visitorService;

    @RequestMapping("/addvisitor")
    public JSONObject addVisitor(@RequestParam("visitorName") String name, @RequestParam("phone") String phone,
                                 @RequestParam("face") MultipartFile facefile, @RequestParam("room_id") Integer roomId,
                                 @RequestParam("power") String power, @RequestParam("documentType") Integer docType,
                                 @RequestParam("documentNumber") String docNumber) throws IOException {
        JSONObject result = new JSONObject();

        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        if (faceService.hasFace(filePath, enginePath)) {
            faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
            byte[] face = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData();
            Visitor visitor = new Visitor();
            visitor.setName(name);
            visitor.setPhoneNumber(phone);
            visitor.setFace(face);
            visitor.setRoomId(roomId);
            visitor.setPower(power);
            visitor.setDocumentType(docType);
            visitor.setDocumentNumber(docNumber);
            int re = visitorService.insertVisitor(visitor);
            if (re == 0) {
                result.put("msg", "true");
            } else
                result.put("msg", "false,数据库插入错误！");
        } else {
            result.put("msg", "false,未检测到人脸信息！");
        }
        return result;
    }
}
