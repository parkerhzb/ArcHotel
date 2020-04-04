package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.*;
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
import java.util.Date;
import java.util.List;

@RestController
public class checkoutController {

    @Autowired
    com.arcsoft.hotel.service.inpersonService inpersonService;
    @Autowired
    com.arcsoft.hotel.service.checkinService checkinService;
    @Autowired
    com.arcsoft.hotel.service.roomService roomService;
    @Autowired
    com.arcsoft.hotel.service.roomConsumeService roomConsumeService;
    @Autowired
    com.arcsoft.hotel.service.checkoutService checkoutService;
    @Autowired
    com.arcsoft.hotel.service.outpersonService outpersonService;

    /*
    前台扫脸退房
    根据人脸信息GET这个人预订的所有房间信息（房间号、房型等）和这个房间的消费总额。
     */
    @RequestMapping("/checkout")
    public JSONObject checkout(@RequestParam("facefile") MultipartFile facefile) throws IOException {
        JSONObject result = new JSONObject();

        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        String facepath = new FileUploadUtil().fileUpload(facefile, OriginPath, "checkout", new DaysUtil().DatetoString(new Date()) + ".jpg");
        File face = new File(facepath);
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        int person_id = -1;
        int checkin_id = -1;
        List<Inperson> inpersonList = inpersonService.getAllNotOut();
        for (Inperson inperson : inpersonList) {
            float similar = faceRecUtil.getFaceSimilar(face, inperson.getFace());
            if (similar > 0.8) {
                person_id = inperson.getId();
                checkin_id = inperson.getCheckinId();
                break;
            }
        }
        if (person_id == -1) {
            result.put("msg", "未找到当前入住信息，请确认是否已经退房");
            return result;
        }
        CheckIn checkIn = checkinService.getById(checkin_id);
        int room_id = checkIn.getRoomId();
        String room_number = roomService.getRoombyId(room_id).getRoomNumber();
        List<RoomConsume> roomConsumes = roomConsumeService.getConsumeByIdANDCheckinDate(room_id, checkIn.getCheckinDate());
        double roomPrice = 0;
        double allPrice = 0;
        for (RoomConsume roomConsume : roomConsumes) {
            if (roomConsume.getItem().equals("房价")) {
                roomPrice = roomConsume.getPrice();
                continue;
            }
            allPrice += roomConsume.getPrice();
        }
        result.put("msg", "0");
        result.put("room_number", room_number);
        result.put("roomPrice", roomPrice);//房价
        result.put("allPrice", allPrice);//房下消费
        result.put("person_id", person_id);
        return result;
    }

    /*
    2020/3/14
    Android ：
    结账后，确认退房
     */
    @RequestMapping("/checkoutforsure")
    public String checkout(@RequestParam("person_id") int person_id, @RequestParam("roomPrice") Double roomPrice,
                           @RequestParam("allPrice") double allPrice) throws ParseException {
        String msg = "";
        int re = 0;
        int checkin_id = inpersonService.getById(person_id).getCheckinId();
        //step1//checkin表标记退房
        re = checkinService.is_check_out(checkin_id);
        if (re <= 0) {
            msg = "退房失败，请重试";
            return msg;
        }
        //step2//inperson表标记退房
        re = inpersonService.is_check_out(checkin_id);
        if (re <= 0) {
            msg = "退房失败，请重试";
            return msg;
        }
        //step3//checkout表添加记录
        CheckOut checkOut = new CheckOut();
        checkOut.setCheckinId(checkin_id);
        checkOut.setPrice(roomPrice + allPrice);
        Date date = new Date();
        DaysUtil daysUtil = new DaysUtil();
        checkOut.setCheckoutDate(daysUtil.initialTime(date));
        checkOut.setTime(daysUtil.DatetoString(date));
        re = checkoutService.addItem(checkOut);
        if (re <= 0) {
            msg = "退房失败，请重试";
            return msg;
        }
        //step4//outperson表添加记录
        int checkout_id = checkoutService.getByCheckin_id(checkin_id).getId();
        Outperson outperson = new Outperson();
        outperson.setCheckinId(checkin_id);
        outperson.setCheckoutId(checkout_id);
        outperson.setInpersonId(person_id);
        re = outpersonService.addItem(outperson);
        if (re <= 0) {
            msg = "退房失败，请重试";
            return msg;
        } else {
            msg = "退房成功，欢迎下次光临";
            return msg;
        }
    }
}
