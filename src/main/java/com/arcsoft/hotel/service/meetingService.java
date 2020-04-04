package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Meeting;
import com.arcsoft.hotel.pojo.MeetingType;

import java.util.List;

public interface meetingService {
    /*
     *2020/3/16
     * 根据id获得一条meet记录
     */
    Meeting getMeetById(int id);

    /**
     * 2020/3/21
     * 返回所有meet_type
     */
    List<MeetingType> getAllType();

    /**
     * 2020/3/22
     * 根据类型获得Type
     */
    MeetingType getTypeByName(String type);

    /**
     * 2020/3/21
     * 根据type与预约状态state返回所有空闲会议室
     * reverse 表示，筛选state还是出了state
     */
    List<Meeting> getFreeMeetByType(int typeId, String state, boolean reverse);
}
