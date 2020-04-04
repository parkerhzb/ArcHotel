package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.RoomConsume;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.roomConsumeService;
import com.arcsoft.hotel.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class consumeController {
    @Autowired
    roomConsumeService roomConsumeService;
    @Autowired
    roomService roomService;
    @Autowired
    checkinService checkinService;
    /*
    添加消费记录
     */
    @RequestMapping("/pay")
    public void consume(@RequestParam("deviceId") String deviceId, @RequestParam("roomId") int roomId,
                        @RequestParam("shoppingName") String shoppingName, @RequestParam("pay") double pay) {
        RoomConsume roomConsume = new RoomConsume();
        roomConsume.setItem(deviceId + "#" + shoppingName);//消费条目
        roomConsume.setRoomId(roomId);//消费房间
        roomConsume.setPrice(pay);
        roomConsume.setTime(new Date());
        roomConsumeService.addConsume(roomConsume);
    }

    /**
     * 2020/3/15
     * android退房时，点击查询详细消费记录
     */
    @RequestMapping("/consumeitem")
    public JSONArray detailedConsume(@RequestParam("room_number") String room_number) {
        JSONArray result = new JSONArray();
        int room_id = roomService.getRoomByNumber(room_number).getId();
        Date checkin_date = checkinService.getByRoomId(room_id).getCheckinDate();
        List<RoomConsume> roomConsumeList = roomConsumeService.getConsumeByIdANDCheckinDate(room_id, checkin_date);
        for (RoomConsume roomConsume : roomConsumeList) {
            JSONObject object = new JSONObject();
            object.put("item", roomConsume.getItem());
            object.put("price", roomConsume.getPrice());
            object.put("time", roomConsume.getTime());
            result.add(object);
        }
        return result;
    }
}
