package com.arcsoft.hotel.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.MeetingReserveMapper;
import com.arcsoft.hotel.pojo.MeetingDate;
import com.arcsoft.hotel.pojo.MeetingReserve;
import com.arcsoft.hotel.pojo.MeetingReserveExample;
import com.arcsoft.hotel.service.meetingDateService;
import com.arcsoft.hotel.service.meetingReserveService;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class meetingReserveServiceImpl implements meetingReserveService {
    @Autowired
    private MeetingReserveMapper meetingReserveMapper;
    @Autowired
    private meetingDateService meetingDateService;

    @Override
    public List getAll(int user_id) {
        MeetingReserveExample meetingReserveExample = new MeetingReserveExample();
        meetingReserveExample.createCriteria().andUserIdEqualTo(user_id);
        List meetingReserves = meetingReserveMapper.selectByExample(meetingReserveExample);
        return meetingReserves;
    }


    @Override
    public int getNewNum(int user_id) {
        //List reserveIds=meetingDateService.getDateGreaterThan(new Date());
        MeetingReserveExample meetingReserveExample = new MeetingReserveExample();
        meetingReserveExample.createCriteria().andUserIdEqualTo(user_id);
        meetingReserveExample.createCriteria().andStatusEqualTo(1);
        //meetingReserveExample.createCriteria().andIdIn(reserveIds);
        int num = meetingReserveMapper.countByExample(meetingReserveExample);
        return num;
    }

    @Override
    public JSONObject reserveMeetingInfo(Date start, Date end) {
        JSONObject result = new JSONObject(true);
        List<MeetingDate> meetingDates = meetingDateService.getFromStoE(start, end);
        JSONObject dateandtime = new JSONObject(true);
        for (MeetingDate meetingDate : meetingDates) {
            String dateStr = new DaysUtil().Date2String(meetingDate.getDate()).substring(2);
            JSONObject timeperiod = new JSONObject(true);
            int period = meetingDate.getTimeperiod();
            if (period == 7) {
                timeperiod.put("m", 2);
                timeperiod.put("a", 2);
                timeperiod.put("e", 2);
            } else if (period == 6) {
                timeperiod.put("m", 0);
                timeperiod.put("a", 2);
                timeperiod.put("e", 2);
            } else if (period == 5) {
                timeperiod.put("m", 2);
                timeperiod.put("a", 0);
                timeperiod.put("e", 2);
            } else if (period == 4) {
                timeperiod.put("m", 0);
                timeperiod.put("a", 0);
                timeperiod.put("e", 2);
            } else if (period == 3) {
                timeperiod.put("m", 2);
                timeperiod.put("a", 2);
                timeperiod.put("e", 0);
            } else if (period == 2) {
                timeperiod.put("m", 2);
                timeperiod.put("a", 0);
                timeperiod.put("e", 2);
            } else if (period == 1) {
                timeperiod.put("m", 2);
                timeperiod.put("a", 0);
                timeperiod.put("e", 0);
            } else if (period == 0) {
                timeperiod.put("m", 0);
                timeperiod.put("a", 0);
                timeperiod.put("e", 0);
            }
            dateandtime.put(dateStr, timeperiod);
        }
        List<Date> dates = new DaysUtil().getBetweenDates(start, end);
        for (Date date : dates) {
            String dateStr = new DaysUtil().Date2String(date).substring(2);
            if (!dateandtime.containsKey(dateStr)) {
                JSONObject timeperiod = new JSONObject(true);
                timeperiod.put("m", 0);
                timeperiod.put("a", 0);
                timeperiod.put("e", 0);
            }
        }
        return null;
    }

    @Override
    public MeetingReserve getById(int id) {
        MeetingReserve meetingReserve = meetingReserveMapper.selectByPrimaryKey(id);
        return meetingReserve;
    }

    @Override
    public int addReserve(MeetingReserve meetingReserve) {
        int re = meetingReserveMapper.insertSelective(meetingReserve);
        return re;
    }
}
