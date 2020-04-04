package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.UserReserve;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface userReserveService {
    //预定房间
    JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut, byte[] face, int user_id) throws ParseException;

    //预定会议室
    JSONObject reserveMeeting(String name, String phone, String roomType, JSONArray meetingPeriod, byte[] face, int user_id) throws ParseException;

    //查询与start(now)，end时间冲突的预定记录
    Map<String, Integer> ConflictItem(Date start, Date end);

    //获取可以now时间入住的所有预约信息
    List<UserReserve> getReserve(Date now);

    //删除预约记录
    int delItem(int id);

    /**
     * 查看某用户所有预定记录
     *
     * @param user_id 用户id
     * @return 预定记录列表
     */
    List getAll(int user_id);

    /**
     * 获得新订单（未执行订单）数量
     *
     * @param user_id 用户id
     * @return 订单数量
     */
    int getNewNum(int user_id);

    /**
     * 查询从start到end之间可用的各房型和详细信息
     *
     * @param start
     * @param end
     * @return
     */
    JSONObject reserveRoomInfo(Date start, Date end);
}
