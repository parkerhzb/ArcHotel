package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.MeetingMapper;
import com.arcsoft.hotel.mapper.MeetingTypeMapper;
import com.arcsoft.hotel.pojo.Meeting;
import com.arcsoft.hotel.pojo.MeetingExample;
import com.arcsoft.hotel.pojo.MeetingType;
import com.arcsoft.hotel.pojo.MeetingTypeExample;
import com.arcsoft.hotel.service.meetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class meetingServiceImpl implements meetingService {
    @Autowired
    MeetingMapper meetingMapper;
    @Autowired
    MeetingTypeMapper meetingTypeMapper;

    @Override
    public Meeting getMeetById(int id) {
        Meeting meeting = meetingMapper.selectByPrimaryKey(id);
        return meeting;
    }

    @Override
    public List<MeetingType> getAllType() {
        List<MeetingType> roomTypeList = meetingTypeMapper.selectByExample(new MeetingTypeExample());
        return roomTypeList;
    }

    @Override
    public MeetingType getTypeByName(String typename) {
        MeetingTypeExample meetingTypeExample = new MeetingTypeExample();
        meetingTypeExample.createCriteria().andNameEqualTo(typename);
        return meetingTypeMapper.selectByExample(meetingTypeExample).get(0);
    }

    @Override
    public List<Meeting> getFreeMeetByType(int typeId, String state, boolean reverse) {
        MeetingExample meetingExample = new MeetingExample();
        if (reverse) {
            switch (state) {
                case "1":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("2");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("4");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("6");
                case "2":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("1");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("4");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("5");
                case "4":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("1");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("2");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("3");
                case "3":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("4");
                case "5":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("2");
                case "6":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("1");
                case "7":
                    meetingExample.or().andTypeIdEqualTo(typeId).andStateEqualTo("0");
            }
        } else
            meetingExample.createCriteria().andTypeIdEqualTo(typeId).andStateEqualTo(state);
        List<Meeting> meetingList = meetingMapper.selectByExample(meetingExample);
        return meetingList;
    }
}
