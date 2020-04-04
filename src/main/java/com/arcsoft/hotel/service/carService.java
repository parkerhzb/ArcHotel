package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Car;

import java.util.List;

public interface carService {
    /**
     * 2020/4/4
     * 返回flag=1的记录，用于更新人脸
     */
    List<Car> getByFlag(int flag);

    /**
     * 2020/4/4
     * 把flag置0，根据车牌号
     */
    int setFlagByNumber(String car_number);
}
