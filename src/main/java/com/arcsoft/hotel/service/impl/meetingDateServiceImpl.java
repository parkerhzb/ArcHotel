package com.arcsoft.hotel.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.MeetingDateMapper;
import com.arcsoft.hotel.pojo.MeetingDate;
import com.arcsoft.hotel.pojo.MeetingDateExample;
import com.arcsoft.hotel.pojo.MeetingReserve;
import com.arcsoft.hotel.service.meetingDateService;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class meetingDateServiceImpl implements meetingDateService {
    @Autowired
    private MeetingDateMapper meetingDateMapper;

    @Override
    public List<Integer> getDateGreaterThan(Date date) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andDateGreaterThan(date);
        List<MeetingDate> meetingDates = meetingDateMapper.selectByExample(meetingDateExample);
        List<Integer> reserveIds = new ArrayList<>();
        for (MeetingDate meetingDate : meetingDates) {
            reserveIds.add(meetingDate.getReserveId());
        }
        return reserveIds;
    }

    @Override
    public List<MeetingDate> getFromStoE(Date start, Date end) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andDateGreaterThanOrEqualTo(start);
        meetingDateExample.createCriteria().andDateLessThanOrEqualTo(end);
        List<MeetingDate> reserveIds = meetingDateMapper.selectByExample(meetingDateExample);
        return reserveIds;
    }

    @Override
    public List<MeetingDate> getByReserveId(int reserveId) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andReserveIdEqualTo(reserveId);
        List<MeetingDate> meetingDates = meetingDateMapper.selectByExample(meetingDateExample);
        return meetingDates;
    }

    @Override
    public JSONObject getAvailableDateAndPeriod(int reserveId) {
        JSONObject result = new JSONObject(true);
        JSONArray dates = new JSONArray();
        List<MeetingDate> meetingDates = getByReserveId(reserveId);
        if (meetingDates != null && meetingDates.size() != 0) {
            int num = 0;
            for (MeetingDate meetingDate : meetingDates) {
                JSONObject date = new JSONObject(true);
                JSONArray period = new JSONArray();
                date.put("date", new DaysUtil().Date2String2(meetingDate.getDate()));
                int time = meetingDate.getTimeperiod();
                if (time == 7) {
                    period.add(2);
                    period.add(2);
                    period.add(2);
                    num += 3;
                } else if (time == 6) {
                    period.add(0);
                    period.add(2);
                    period.add(2);
                    num += 2;
                } else if (time == 5) {
                    period.add(2);
                    period.add(0);
                    period.add(2);
                    num += 2;
                } else if (time == 4) {
                    period.add(0);
                    period.add(0);
                    period.add(2);
                    num += 1;
                } else if (time == 3) {
                    period.add(2);
                    period.add(2);
                    period.add(0);
                    num += 2;
                } else if (time == 2) {
                    period.add(0);
                    period.add(2);
                    period.add(0);
                    num += 1;
                } else if (time == 1) {
                    period.add(2);
                    period.add(0);
                    period.add(0);
                    num += 1;
                } else if (time == 0) {
                    period.add(0);
                    period.add(0);
                    period.add(0);
                    num += 0;
                }
                date.put("period", period);
                dates.add(date);
            }
            result.put("usedPeriodNum", num);
            result.put("dates", dates);
            return result;
        }
        result.put("usedPeriodNum", 0);
        return result;
    }

    @Override
    public JSONObject getMyDateAndPeriod(int reserveId) {
        JSONObject result = new JSONObject(true);
        JSONArray dates = new JSONArray();
        List<MeetingDate> meetingDates = getByReserveId(reserveId);
        if (meetingDates != null && meetingDates.size() != 0) {
            int num = 0;
            for (MeetingDate meetingDate : meetingDates) {
                JSONObject date = new JSONObject(true);
                JSONArray period = new JSONArray();
                date.put("date", new DaysUtil().Date2String2(meetingDate.getDate()));
                int time = meetingDate.getTimeperiod();
                if (time == 7) {
                    period.add(1);
                    period.add(1);
                    period.add(1);
                    num += 3;
                } else if (time == 6) {
                    period.add(0);
                    period.add(1);
                    period.add(1);
                    num += 2;
                } else if (time == 5) {
                    period.add(1);
                    period.add(0);
                    period.add(1);
                    num += 2;
                } else if (time == 4) {
                    period.add(0);
                    period.add(0);
                    period.add(1);
                    num += 1;
                } else if (time == 3) {
                    period.add(1);
                    period.add(1);
                    period.add(0);
                    num += 2;
                } else if (time == 2) {
                    period.add(0);
                    period.add(1);
                    period.add(0);
                    num += 1;
                } else if (time == 1) {
                    period.add(1);
                    period.add(0);
                    period.add(0);
                    num += 1;
                } else if (time == 0) {
                    period.add(0);
                    period.add(0);
                    period.add(0);
                    num += 0;
                }
                date.put("period", period);
                dates.add(date);
            }
            result.put("usedPeriodNum", num);
            result.put("dates", dates);
            return result;
        }
        result.put("usedPeriodNum", 0);
        return result;
    }

    @Override
    public List<MeetingDate> getByDate(Date date) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andDateEqualTo(date);
        List<MeetingDate> result = meetingDateMapper.selectByExample(meetingDateExample);
        return result;
    }

    @Override
    public List<MeetingDate> getByDateANDHour(Date date, int period) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andDateEqualTo(date).andTimeperiodEqualTo(period);
        List<MeetingDate> result = meetingDateMapper.selectByExample(meetingDateExample);
        return result;
    }

    @Override
    public List<MeetingDate> getByRe_id(int re_id) {
        MeetingDateExample meetingDateExample = new MeetingDateExample();
        meetingDateExample.createCriteria().andReserveIdEqualTo(re_id);
        List<MeetingDate> re = meetingDateMapper.selectByExample(meetingDateExample);
        return re;
    }

    @Override
    public int addMeetDate(MeetingDate meetingDate) {
        int re = meetingDateMapper.insertSelective(meetingDate);
        return re;
    }
}
