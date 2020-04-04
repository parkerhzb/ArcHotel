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

    @RequestMapping(value = "/wfaceLogin", produces = "application/json;charset=UTF-8")
    public JSONObject faceLogin(@RequestPart("imgData") MultipartFile file, @RequestParam("sess_id") String sess_id) throws ServletException, IOException {
        System.out.println("开始处理微信登录……");

        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        String filePath = fileUploadUtil.fileUpload(file, OriginPath, "user", sess_id);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        JSONObject result = new JSONObject();
        if (faceService.hasFace(filePath, enginePath)) {
            Inperson inperson = wxLoginService.wxLogin(filePath, enginePath, sess_id);
            if (inperson != null) {  //住客
                try {
                    JSONObject userInfo_js = new JSONObject();
                    JSONObject user_js = new JSONObject();
                    JSONObject room_js = new JSONObject();

                    //房客信息
                    CheckIn checkIn = checkinService.getById(inperson.getCheckinId());
                    Room room = roomService.getRoombyId(checkIn.getRoomId());
                    RoomType roomType = roomTypeService.getRoomTypeById(room.getTypeId());
                    Date checkin_date = checkinService.getCheckinDate(checkIn.getRoomId());
                    DaysUtil daysUtil = new DaysUtil();
                    Date date = new Date(); // get current date
                    date = daysUtil.initialTime(date);
                    List<RoomConsume> roomConsumes = roomConsumeService.getConsumeByIdANDCheckinDate(checkIn.getRoomId(), checkin_date);
                    //List<CheckOut> checkOuts = checkoutService.getCheckoutByTypeANDNumber(inperson.getDocumentType(), inperson.getDocumentNumber());

                    int days = daysUtil.getDistanceTime(checkin_date, date);

                    userInfo_js.put("name", inperson.getName());
                    userInfo_js.put("phone", inperson.getPhoneNumber());

                    user_js.put("name", inperson.getName());
                    user_js.put("phone", inperson.getPhoneNumber());
                    user_js.put("status", "true");

                    JSONObject totalBill = new JSONObject();
                    //double amount = days * roomType.getPrice();
                    double totalbill = 0;
                    if (roomConsumes != null && roomConsumes.size() != 0) {
                        for (RoomConsume roomConsume : roomConsumes) {
                            totalbill += roomConsume.getPrice();
                        }
                    }
                    totalBill.put("num", totalbill);

                    JSONObject items = new JSONObject();
                    items.put("roomNum", room.getRoomNumber());
                    items.put("amount", totalbill);
                    totalBill.put("items", items);
                    user_js.put("totalBill", totalBill);

                    JSONObject historyOrder = wxLoginService.historyOrder(sess_id);
                    user_js.put("history", historyOrder);
                    result.put("user", user_js);

                    //房间信息
                    room_js.put("num", room.getRoomNumber());
                    room_js.put("amount", totalbill);
                    String url = "https://drcxs.cn/images/type_ID_.jpg";
                    url.replaceAll("_ID_", room.getTypeId().toString());
                    room_js.put("img", url);

                    JSONArray billArr = new JSONArray();
                    if (roomConsumes != null && roomConsumes.size() != 0) {
                        for (RoomConsume roomConsume : roomConsumes) {
                            JSONObject item = new JSONObject(true);
                            item.put("itemName", roomConsume.getItem());
                            item.put("itemTime", daysUtil.Date2String3(roomConsume.getTime()));
                            item.put("itemAccount", roomConsume.getPrice());
                            billArr.add(item);
                        }
                    }
                    room_js.put("bill", billArr);
                    result.put("room", room_js);



                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("error", "读取住户信息出错！");
                }
            } else {    //访客
                result.put("error", "当前用户非房客！");
            }
        } else {
            result.put("error", "未检测到人脸信息！");
        }
        return result;
    }
}
