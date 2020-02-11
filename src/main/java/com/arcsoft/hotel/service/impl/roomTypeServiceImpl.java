package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.pojo.RoomType;
import com.arcsoft.hotel.service.roomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roomTypeServiceImpl implements roomTypeService {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Override
    public RoomType getRoomTypeById(Integer Id) {
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(Id);
        return roomType;
    }
}
