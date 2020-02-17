package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.RoomConsumeMapper;
import com.arcsoft.hotel.pojo.RoomConsume;
import com.arcsoft.hotel.pojo.RoomConsumeExample;
import com.arcsoft.hotel.service.roomConsumeService;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class roomConsumeServiceImpl implements roomConsumeService {

    @Autowired
    RoomConsumeMapper roomConsumeMapper;

    @Override
    public List<RoomConsume> getConsumeByIdANDCheckinDate(Integer roomId, Date time) {
        RoomConsumeExample roomConsumeExample = new RoomConsumeExample();
        roomConsumeExample.createCriteria().andIdEqualTo(roomId).andTimeBetween(time, new Date());
        List<RoomConsume> roomConsumes = roomConsumeMapper.selectByExample(roomConsumeExample);
        return roomConsumes;
    }

    @Override
    public void addConsume(RoomConsume roomConsume) {
        roomConsumeMapper.insertSelective(roomConsume);
    }
}
