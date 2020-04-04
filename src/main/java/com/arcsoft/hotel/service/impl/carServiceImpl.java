package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CarMapper;
import com.arcsoft.hotel.pojo.Car;
import com.arcsoft.hotel.pojo.CarExample;
import com.arcsoft.hotel.service.carService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carServiceImpl implements carService {
    @Autowired
    CarMapper carMapper;

    @Override
    public List<Car> getByFlag(int flag) {
        CarExample carExample = new CarExample();
        carExample.createCriteria().andFlagEqualTo(flag);
        return carMapper.selectByExampleWithBLOBs(carExample);
    }

    @Override
    public int setFlagByNumber(String car_number) {
        CarExample carExample = new CarExample();
        carExample.createCriteria().andCarNumberEqualTo(car_number);
        Car car = new Car();
        car.setFlag(0);
        int re = carMapper.updateByExampleSelective(car, carExample);
        return re;
    }
}
