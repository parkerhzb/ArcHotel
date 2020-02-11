package com.arcsoft.hotel.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.UserReserveMapper;
import com.arcsoft.hotel.pojo.UserReserve;
import com.arcsoft.hotel.pojo.UserReserveExample;
import com.arcsoft.hotel.service.userReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

@Service
public class UserReserveServiceImpl implements userReserveService {
    @Autowired
    UserReserveMapper userReserveMapper;
    UserReserveExample userReserveExample = new UserReserveExample();

    @Override
    public JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut) {
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
            if (userReserveMapper.insertSelective(userReserve) > 0) {
                json.put("ok", 1);
            } else
                json.put("ok", 0);
        } else
            json.put("ok", -1);
        return json;
    }
}
