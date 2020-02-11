package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.RoomConsume;

import java.util.Date;
import java.util.List;

public interface roomConsumeService {
    List<RoomConsume> getConsumeByIdANDCheckinDate(Integer roomId, Date time);
}
