package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.service.faceService;
import com.arcsoft.hotel.service.userReserveService;
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
import java.util.List;

/*预定功能

 */
@RestController
public class reserveController {

    @Autowired
    userReserveService userReserveService;
    @Autowired
    faceService faceService;

    @RequestMapping("/reserve")
    public JSONObject reserve(@RequestParam("reserveType") String rsvType, @RequestParam("roomType") String roomType,
                              @RequestParam("rsvName") String name, @RequestParam("rsvPhone") String phone,
                              @RequestParam("rsvRoomNum") String numStr, @RequestParam("checkInTime") String CheckIn,
                              @RequestParam("checkOutTime") String CheckOut, @RequestParam("face") MultipartFile facefile) throws IOException {

        JSONObject result = new JSONObject();
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
//        if(!faceService.hasFace(filePath,enginePath)){
//            result.put("success",0);
//            result.put("error","照片识别失败！");
//            return result;
//        }
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        byte[] face = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData();
        //住房预定
        if (rsvType.equals("0")) {
            int num = -1;
            if (numStr == null || numStr.equals("")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
                return result;
            }
            num = Integer.parseInt(numStr.trim());
            JSONObject isSuccess = userReserveService.reserveRoom(name, phone, roomType, num, CheckIn, CheckOut);
            if (isSuccess.get("ok").equals("-1")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
            } else if (isSuccess.get("ok").equals("0")) {
                result.put("success", 0);
                result.put("error", "预定失败！");
            } else {
                result.put("success", 1);
            }
            return result;

        }
        //会议室预订
        else if (rsvType.equals("1")) {

            return result;
        }
        return result;
    }
}
