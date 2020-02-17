package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample;
import com.arcsoft.hotel.service.checkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class checkinServiceImpl implements checkinService {
    @Autowired
    CheckInMapper checkInMapper;
    CheckInExample checkInExample = new CheckInExample();

    @Override
    public List<CheckIn> getAll() {
        List<CheckIn> list = checkInMapper.selectByExampleWithBLOBs(checkInExample);
        return list;
    }

    @Override
    public Date getCheckinDate(Integer roomId) {
        checkInExample.createCriteria().andRoomIdEqualTo(roomId).andIsCheckOutIsNull();
        Date checkin_date = checkInMapper.selectByExample(checkInExample).get(0).getCheckinDate();
        return checkin_date;
    }

    @Override
    public ArrayList<byte[]> faceList(int roomId) {
        checkInExample.createCriteria().andRoomIdEqualTo(roomId).andIsCheckOutIsNull();
        List<CheckIn> person = checkInMapper.selectByExample(checkInExample);
        ArrayList<byte[]> faceList = new ArrayList<>();
        for (CheckIn checkIn : person) {
            faceList.add(checkIn.getFace());
        }
        return faceList;
    }


    @Override
    public ArrayList<byte[]> faceList() {
        checkInExample.createCriteria().andIsCheckOutIsNull();
        List<CheckIn> person = checkInMapper.selectByExample(checkInExample);
        ArrayList<byte[]> faceList = new ArrayList<>();
        for (CheckIn checkIn : person) {
            faceList.add(checkIn.getFace());
        }
        return faceList;
    }

    @Override
    public int addCheckin(CheckIn checkIn) {
        int re = checkInMapper.insertSelective(checkIn);
        return re;
    }
}
