package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.UserReserve;

public interface userReserveService {
    JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut);
}
