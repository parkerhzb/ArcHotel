package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.MeetingTypeMapper;
import com.arcsoft.hotel.pojo.MeetingType;
import com.arcsoft.hotel.pojo.MeetingTypeExample;
import com.arcsoft.hotel.service.meetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class meetingTypeServiceImpl implements meetingTypeService {
    @Autowired
    private MeetingTypeMapper meetingTypeMapper;

    @Override
    public MeetingType getByName(String name) {
        MeetingTypeExample meetingTypeExample = new MeetingTypeExample();
        meetingTypeExample.createCriteria().andNameEqualTo(name);
        List<MeetingType> meetingTypes = meetingTypeMapper.selectByExample(meetingTypeExample);
        if (meetingTypes != null && meetingTypes.size() != 0)
            return meetingTypes.get(0);
        return null;
    }
}
