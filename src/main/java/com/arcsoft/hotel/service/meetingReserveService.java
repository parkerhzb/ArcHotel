package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.MeetingReserve;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface meetingReserveService {
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
     * 查询从start到end之间可用的各会议室类型和详细信息
     *
     * @param start
     * @param end
     * @return
     */
    JSONObject reserveMeetingInfo(Date start, Date end);

    /**
     * 2020/3/16
     * 根据id获得记录
     */
    MeetingReserve getById(int id);

    /**
     * 2020/3/22
     * 添加记录
     */
    int addReserve(MeetingReserve meetingReserve);
}
