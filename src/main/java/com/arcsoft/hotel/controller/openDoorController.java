package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.Inperson;
import com.arcsoft.hotel.pojo.Invitation;
import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.inpersonService;
import com.arcsoft.hotel.service.invitationService;
import com.arcsoft.hotel.service.visitorService;
import com.arcsoft.hotel.util.DaysUtil;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/open")
public class openDoorController {

    @Autowired
    checkinService checkinService;
    @Autowired
    invitationService invitationService;
    @Autowired
    visitorService visitorService;

    @Autowired
    inpersonService inpersonService;

    @RequestMapping("/door")
    public String opendoor(@RequestParam("face") MultipartFile facefile, @RequestParam("rommId") int roomId) throws IOException, ParseException {
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);
        File file = new File(filePath);

        //String enginePath=OriginPath+File.separator+"lib"+File.separator+"LINUX64";
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        String result = "0";
        //房客
        ArrayList<byte[]> Faces1 = checkinService.faceList(roomId);
        for (byte[] face : Faces1) {
            if (faceRecUtil.getFaceSimilar(file, face) > 0.8) {
                result = "1";
                return result;
            }
        }
        //访客
        List<Invitation> invitationList = invitationService.getByRoomid(roomId);
        Date date = new DaysUtil().initialTime(new Date());
        for (Invitation invitation : invitationList) {
            if (invitation.getTime().after(date)) {
                int invite_id = invitation.getId();
                List<Visitor> visitorList = visitorService.getByIvtId(invite_id);
                for (Visitor visitor : visitorList) {
                    if (visitor.getPower().equals("2") || visitor.getPower().equals("3")) {
                        if (faceRecUtil.getFaceSimilar(file, visitor.getFace()) > 0.8) {
                            result = "1";
                            return result;
                        }
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping("shop")
    public int openshop(@RequestParam("face") MultipartFile facefile) throws IOException {
        int roomId = -1;
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);
        File file = new File(filePath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        //获得未退房的所有房客
        List<Inperson> inpersonList = inpersonService.getAllNotOut();
        for (Inperson inperson : inpersonList) {
            if (faceRecUtil.getFaceSimilar(file, inperson.getFace()) > 0.8) {
                CheckIn checkIn = checkinService.getById(inperson.getCheckinId());
                roomId = checkIn.getRoomId();
                return roomId;
            }
        }
        return roomId;
    }

}
