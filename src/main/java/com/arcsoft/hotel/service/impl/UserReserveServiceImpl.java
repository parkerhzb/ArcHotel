package com.arcsoft.hotel.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.mapper.UserReserveMapper;
import com.arcsoft.hotel.pojo.RoomType;
import com.arcsoft.hotel.pojo.RoomTypeExample;
import com.arcsoft.hotel.pojo.UserReserve;
import com.arcsoft.hotel.pojo.UserReserveExample;
import com.arcsoft.hotel.service.userReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserReserveServiceImpl implements userReserveService {

    @Autowired
    UserReserveMapper userReserveMapper;
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Override
    public JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut, byte[] face) {
        JSONObject json = new JSONObject();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (checkIn != null && checkOut != null) {
            Date checkInDate = Date.valueOf(checkIn);
            Date checkOutDate = Date.valueOf(checkOut);
            UserReserve userReserve = new UserReserve();
            userReserve.setName(name);
            userReserve.setPhoneNumber(phone);
            userReserve.setRoomType(roomType);
            userReserve.setNum(num);
            userReserve.setCheckinDate(checkInDate);
            userReserve.setCheckoutDate(checkOutDate);
            userReserve.setTime(time);
            userReserve.setFace(face);
            if (userReserveMapper.insertSelective(userReserve) > 0) {
                json.put("ok", 1);
            } else
                json.put("ok", 0);
        } else
            json.put("ok", -1);
        return json;
    }

    @Override
    public Map<String, Integer> ConflictItem(java.util.Date end) {
        //初始化房型与数量，初始值为0
        Map<String, Integer> list = new HashMap<>();
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        List<RoomType> roomTypes = roomTypeMapper.selectByExample(roomTypeExample);
        for (RoomType roomType : roomTypes) {
            list.put(roomType.getName(), 0);
        }
        //已经预定中冲突的房间
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andCheckinDateLessThanOrEqualTo(end);
        List<UserReserve> userReserves = userReserveMapper.selectByExampleWithBLOBs(userReserveExample);
        for (UserReserve userReserve : userReserves) {
            String type = userReserve.getRoomType();
            int oldValue = list.get(type);
            list.replace(type, ++oldValue);
        }
        return list;
    }

    @Override
    public List<UserReserve> getReserve(java.util.Date now) {
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andCheckinDateLessThanOrEqualTo(now).andCheckoutDateGreaterThan(now);
        List<UserReserve> list = userReserveMapper.selectByExampleWithBLOBs(userReserveExample);
        return list;
    }

    @Override
    public int delItem(int id) {
        int re = roomTypeMapper.deleteByPrimaryKey(id);
        return re;
    }
}
