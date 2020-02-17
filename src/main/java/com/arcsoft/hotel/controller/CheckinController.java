package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.roomService;
import com.arcsoft.hotel.service.userReserveService;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.ImageUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CheckinController {
    @Autowired
    roomService roomService;
    @Autowired
    checkinService checkinService;
    @Autowired
    userReserveService userReserveService;

    @RequestMapping(value = "/checkin", produces = "application/json;charset=UTF-8")
    public JSONArray buyRoom(@RequestBody JSONObject list) throws IOException {
        JSONArray result = new JSONArray();

        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        OriginPath = OriginPath + File.separator + "checkin";

        //String enginePath=OriginPath+File.separator+"lib"+File.separator+"LINUX64";
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        ImageUtil imageUtil = new ImageUtil();
        JSONArray payList = list.getJSONArray("list");
        for (int i = 0; i < payList.size(); ++i) {
            JSONObject json = payList.getJSONObject(i);
            int id = json.getInteger("id");
            int typeId = json.getInteger("typeId");
            Date checkinDate = json.getDate("checkinDate");
            Date checkoutDate = json.getDate("checkoutDate");
            //分配房间号
            //获得所有typeId的空room
            List<Room> AvailabeRooms = roomService.EmptyRoombyTypeId(typeId);
            int roomId;
            String roomNumber;
            if (AvailabeRooms.size() != 0) {//有余量，分配就近原则
                roomNumber = AvailabeRooms.get(0).getRoomNumber();
                roomId = AvailabeRooms.get(0).getId();
                JSONObject resultJson = new JSONObject();
                resultJson.put("roomId", roomId);
                result.add(resultJson);
            } else {//无余量，返回错误信息
                JSONObject resultJson = new JSONObject();
                resultJson.put("error", "房间余量为空");
                result.add(resultJson);
                return result;
            }
            //每个房间内的人员
            int num = json.getInteger("num");
            JSONArray persons = json.getJSONArray("person");
            for (int j = 0; j < persons.size(); ++j) {
                JSONObject person = persons.getJSONObject(j);
                String name = person.getString("name");
                String gender = person.getString("gender");
                int docType = person.getInteger("docType");
                String docNumber = person.getString("docNumber");
                String faceFile = json.getString("face");
                String phone = json.getString("phone");
                //OriginPath/checkin/roomNumber/xxx.jpg
                String path = OriginPath + File.separator + "checkin" + File.separator + roomNumber + File.separator + name + ".jpg";
                imageUtil.savePhoto(faceFile, path);
                byte[] face = faceRecUtil.getFaceFeature(new File(path)).getFeatureData();//获取特征值
                CheckIn checkIn = new CheckIn();
                checkIn.setName(name);
                checkIn.setDocumentType(docType);
                checkIn.setDocumentNumber(docNumber);
                checkIn.setGender(gender);
                checkIn.setFace(face);
                checkIn.setCheckinDate(checkinDate);
                checkIn.setCheckoutDate(checkoutDate);

                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                checkIn.setTime(sdf.format(date));
                checkIn.setRoomId(roomId);
                //relatedRoom   is_check_out
                checkIn.setPhoneNumber(phone);
                //添加checkin记录
                checkinService.addCheckin(checkIn);
            }
            if (id != -1) {
                //预约者入住 删除预约记录
                userReserveService.delItem(id);
            }
        }
        return result;
    }
}
