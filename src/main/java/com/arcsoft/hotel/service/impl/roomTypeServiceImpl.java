package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.pojo.RoomType;
import com.arcsoft.hotel.pojo.RoomTypeExample;
import com.arcsoft.hotel.service.roomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomTypeServiceImpl implements roomTypeService {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Override
    public RoomType getRoomTypeById(Integer Id) {
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(Id);
        return roomType;
    }

    @Override
    public List<RoomType> getAll() {
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        List<RoomType> list = roomTypeMapper.selectByExample(roomTypeExample);
        return list;
    }

    @Override
    public double getPriceByName(String typeName) {
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andNameEqualTo(typeName);
        double price = roomTypeMapper.selectByExample(roomTypeExample).get(0).getPrice();
        return price;
    }
}
