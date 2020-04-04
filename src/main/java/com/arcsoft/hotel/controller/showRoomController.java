package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.pojo.RoomType;
import com.arcsoft.hotel.pojo.RoomTypeExample;
import com.arcsoft.hotel.service.roomService;
import com.arcsoft.hotel.service.roomTypeService;
import com.arcsoft.hotel.service.userReserveService;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class showRoomController {
    @Autowired
    roomService roomService;
    @Autowired
    userReserveService userReserveService;
    @Autowired
    roomTypeService roomTypeService;
    @RequestMapping("/showroom")
    public JSONArray showRoom(@RequestParam("checkinDate") String start, @RequestParam("checkoutDate") String end) {
        DaysUtil daysUtil = new DaysUtil();
        Date checkinDate = daysUtil.StringtoDate(start, "yyyy-MM-dd");
        Date checkoutDate = daysUtil.StringtoDate(end, "yyyy-MM-dd");
        JSONArray result = new JSONArray();
//        //当前入住的冲突房间
//        Map<String ,Integer>types1 =roomService.ConflictTypeFromCheckin(checkinDate,checkoutDate);
        //返回当前未入住的房型与数量
        Map<String, Integer> types1 = roomService.EmptyTypeNum();
        //与预约冲突的房间类型
        Map<String, Integer> types2 = userReserveService.ConflictItem(new Date(), checkoutDate);
        List<RoomType> roomTypes = roomTypeService.getAll();
        for (RoomType roomType : roomTypes) {
            JSONObject json = new JSONObject();
            String type = roomType.getName();
//            int Max=roomService.getTypeNum(roomType.getId());
            int num1 = types1.get(type);
            int num2 = types2.get(type);
            json.put("typeId", roomType.getId());
            json.put("type", type);
            json.put("num", num1 - num2);
            json.put("price", roomTypeService.getRoomTypeById(roomType.getId()).getPrice());
            result.add(json);
        }
        return result;
    }
}
