package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.RoomConsume;

import java.util.Date;
import java.util.List;

public interface roomConsumeService {
    //查询roomId房间当前为止所有消费记录
    List<RoomConsume> getConsumeByIdANDCheckinDate(Integer roomId, Date time);

    //在roomId房下新增消费记录
    void addConsume(RoomConsume roomConsume);
}
