package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.MeetingAttendeesMapper;
import com.arcsoft.hotel.pojo.MeetingAttendees;
import com.arcsoft.hotel.pojo.MeetingAttendeesExample;
import com.arcsoft.hotel.service.meetingattendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class meetingattendServiceImpl implements meetingattendService {
    @Autowired
    MeetingAttendeesMapper meetingAttendeesMapper;

    @Override
    public List<MeetingAttendees> getByStatus() {
        MeetingAttendeesExample meetingAttendeesExample = new MeetingAttendeesExample();
        meetingAttendeesExample.createCriteria().andStatusEqualTo(0);
        List<MeetingAttendees> result = meetingAttendeesMapper.selectByExampleWithBLOBs(meetingAttendeesExample);
        return result;
    }

    @Override
    public List<MeetingAttendees> getByReserveId(int re_id) {
        MeetingAttendeesExample meetingAttendeesExample = new MeetingAttendeesExample();
        meetingAttendeesExample.createCriteria().andReserveIdEqualTo(re_id);
        List<MeetingAttendees> result = meetingAttendeesMapper.selectByExampleWithBLOBs(meetingAttendeesExample);
        return result;
    }

    @Override
    public int addAttendees(MeetingAttendees meetingAttendees) {
        int re = meetingAttendeesMapper.insertSelective(meetingAttendees);
        return re;
    }
}
