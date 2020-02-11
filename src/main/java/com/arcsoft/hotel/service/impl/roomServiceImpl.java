package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.RoomMapper;
import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.pojo.RoomExample;
import com.arcsoft.hotel.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roomServiceImpl implements roomService {
    @Autowired
    RoomMapper roomMapper;
    RoomExample roomExample = new RoomExample();

    @Override
    public Room getRoombyId(Integer Id) {
        Room room = roomMapper.selectByPrimaryKey(Id);
        return room;
    }
}
