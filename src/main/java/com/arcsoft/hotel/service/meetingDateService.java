package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.MeetingDate;
import com.arcsoft.hotel.pojo.MeetingReserve;

import java.util.Date;
import java.util.List;

public interface meetingDateService {
    /**
     * 查询date日期之后的预定会议室的具体日期记录
     *
     * @param date
     * @return
     */
    List<Integer> getDateGreaterThan(Date date);

    /**
     * 查询从start到end间的所有日期记录
     *
     * @param start
     * @param end
     * @return
     */
    List<MeetingDate> getFromStoE(Date start, Date end);

    /**
     * 根据预定id查询对应的日期记录
     *
     * @param reserveId
     * @return
     */
    List<MeetingDate> getByReserveId(int reserveId);

    /**
     * 根据预定id获得对应日期及可用的时间段（0可用，2不可用）和已使用的时间段的数目
     *
     * @param reserveId
     * @return
     */
    JSONObject getAvailableDateAndPeriod(int reserveId);

    /**
     * 根据预定id获得对应日期及已预定的时间段（0无预定，1有预定）和已使用的时间段的数目
     *
     * @param reserveId
     * @return
     */
    JSONObject getMyDateAndPeriod(int reserveId);

    /**
     * 2020/3/16
     * 获取date日期下的所有记录
     */
    List<MeetingDate> getByDate(Date date);

    /**
     * 2020/3/16
     * 获取date日期下不同时间断的的所有记录。
     */
    List<MeetingDate> getByDateANDHour(Date date, int period);

    /**
     * 2020/3/22
     * 根据reserve_id获取记录
     */
    List<MeetingDate> getByRe_id(int re_id);

    /**
     * 2020/3/23
     * 添加记录
     */
    int addMeetDate(MeetingDate meetingDate);
}
