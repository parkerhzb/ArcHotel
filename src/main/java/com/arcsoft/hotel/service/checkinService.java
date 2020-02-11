package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.CheckIn;

import java.util.Date;
import java.util.List;

public interface checkinService {
    List<CheckIn> getAll();

    Date getCheckinDate(Integer roomId);
}
