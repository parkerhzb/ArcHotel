package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.UserReserve;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface userReserveService {
    //插入预定信息
    JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut, byte[] face);

    //查询与start(now)，end时间冲突的预定记录
    Map<String, Integer> ConflictItem(Date end);

    //获取可以now时间入住的所有预约信息
    List<UserReserve> getReserve(Date now);

    //删除预约记录
    int delItem(int id);
}
