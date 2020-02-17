package com.arcsoft.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.*;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/*
微信扫脸登录
输入：拍摄的人脸
输出：登录用户的信息
 */
@RestController
public class wxLoginController {

    @Autowired
    faceService faceService;
    @Autowired
    roomService roomService;
    @Autowired
    roomTypeService roomTypeService;
    @Autowired
    WxLoginService wxLoginService;
    @Autowired
    checkinService checkinService;
    @Autowired
    roomConsumeService roomConsumeService;
    @Autowired
    checkoutService checkoutService;

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestBody MultipartFile file) throws ServletException, IOException {
        System.out.println("开始处理微信登录……");
        JSONObject json = new JSONObject();

        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        //System.out.println(OriginPath);
        String filePath = fileUploadUtil.fileUpload(file, OriginPath);

        //String enginePath=OriginPath+File.separator+"lib"+File.separator+"LINUX64";
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        //System.out.println(enginePath);
        JSONObject result = new JSONObject();
        if (faceService.hasFace(filePath, enginePath)) {
            CheckIn checkIn = wxLoginService.wxLogin(filePath, enginePath);
            //System.out.println(checkIn.toString());
            if (checkIn != null) {
                try {
                    Room room = roomService.getRoombyId(checkIn.getRoomId());
                    RoomType roomType = roomTypeService.getRoomTypeById(room.getTypeId());
//                  sql = "SELECT * FROM room_consume where room_id=? and time<NOW() and ttime>(select checkin_date from check_in where room_id=? and is_check_out=0)";
                    Date checkin_date = checkinService.getCheckinDate(checkIn.getRoomId());
                    DaysUtil daysUtil = new DaysUtil();
                    Date date = new Date(); // get the current date
                    date = daysUtil.initialTime(date);
                    List<RoomConsume> roomConsumes = roomConsumeService.getConsumeByIdANDCheckinDate(checkIn.getRoomId(), checkin_date);
                    List<CheckOut> checkOuts = checkoutService.getCheckoutByTypeANDNumber(checkIn.getDocumentType(), checkIn.getDocumentNumber());

                    int days = daysUtil.getDistanceTime(checkin_date, date);

                    String iconPath = OriginPath + File.separator + "icon" + File.separator + checkIn.getId() + ".jpg";
                    json.put("id", checkIn.getId());
                    json.put("name", checkIn.getName());
                    json.put("avatar", iconPath);
                    json.put("phone", checkIn.getPhoneNumber());
                    json.put("status", 1);
                    JSONArray totalBillArray = new JSONArray();
                    JSONObject billJson = new JSONObject();
                    billJson.put("roomNum", room.getRoomNumber());
                    double amount = days * roomType.getPrice();
                    for (RoomConsume roomConsume : roomConsumes) {
                        amount += roomConsume.getPrice();
                    }
                    billJson.put("amount", amount);
                    totalBillArray.add(billJson);
                    json.put("totalBill", totalBillArray);
                    JSONArray historyOrderArray = new JSONArray();
                    for (CheckOut checkOut : checkOuts) {
                        JSONObject historyOrder = new JSONObject();
                        historyOrder.put("year", checkOut.getCheckinDate().toString().substring(0, 4));
                        historyOrder.put("checkIn", checkOut.getCheckinDate().toString().substring(5, 10));
                        historyOrder.put("checkOut", checkOut.getCheckoutDate().toString().substring(5, 10));
                        historyOrder.put("roomNum", roomService.getRoombyId(checkIn.getRoomId()).getRoomNumber());
                        historyOrder.put("amount", checkOut.getPrice());
                        historyOrderArray.add(historyOrder);
                    }
                    json.put("history", historyOrderArray);
                    result.put("user", json);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("error", "读取住户信息出错！");
                }
//            }
//            else if (flag == 0) {
//                int id = Integer.parseInt(checkIn.get("id").toString());
//                json.put("id", id);
//                json.put("status", 0);
//                result.put("user", json);
//            }
//            else if (flag == -1) {
//                result.put("error", "SQL错误！");
            } else {
                result.put("error", "数据库连接失败或其他错误！");
            }
        } else {
            result.put("error", "未检测到人脸信息！");
        }
        return result;
    }
}
