package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.CheckIn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface checkinService {
    List<CheckIn> getAll();
    //获得roomId住户入住时间
    Date getCheckinDate(Integer roomId);

    //获得roomId在住房客人脸
    ArrayList<byte[]> faceList(int roomId);

    //获得所有在住的房客人脸
    ArrayList<byte[]> faceList();

    //添加入住记录
    int addCheckin(CheckIn checkIn);

    /*
   2020/3/14
   根据id获得一条记录
    */
    CheckIn getById(int id);

    /*
    2020/3/14
    根据id标记退房
     */
    int is_check_out(int id);

    /**
     * 2020/3/15
     * 根据room_id获得当前入住的记录
     */
    CheckIn getByRoomId(int room_id);
}
