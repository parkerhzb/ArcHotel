package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.RoomType;

import java.util.List;

public interface roomTypeService {
    RoomType getRoomTypeById(Integer id);

    List<RoomType> getAll();

    //根据类型名获得价格
    double getPriceByName(String typeName);
}
