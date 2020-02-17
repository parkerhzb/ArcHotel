package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface roomService {
    Room getRoombyId(Integer Id);

    //根据入住退房时间获取冲突的房间
    Map<String, Integer> ConflictTypeFromCheckin(Date start, Date end);//返回房型与数量

    ArrayList<Integer> ConflictRoomFromCheckin(Date start, Date end);//返回房间id
    //返回当前没入住的房型与数量
    Map<String, Integer> EmptyTypeNum();

    //获得所有房间
    List<Room> getAll();

    //根据房型获得没有冲突的房间
    List<Room> EmptyRoombyTypeId(int typeId);

    //获得typrId房型数量
    int getTypeNum(int typeId);
}
