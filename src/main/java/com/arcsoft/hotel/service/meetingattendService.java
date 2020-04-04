package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.MeetingAttendees;

import java.util.List;

public interface meetingattendService {
    /**
     * 2020/3/16
     * 获得所有未签到的(status=0)的记录
     */
    List<MeetingAttendees> getByStatus();

    /**
     * 2020/3/16
     * 根据reserve_id返回参会人员
     */
    List<MeetingAttendees> getByReserveId(int re_id);

    /**
     * 2020/3/23
     * 添加参会人
     */
    int addAttendees(MeetingAttendees meetingAttendees);
}
