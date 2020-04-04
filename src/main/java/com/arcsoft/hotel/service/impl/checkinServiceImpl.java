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

    @Override
    public List<CheckIn> getAll() {
        CheckInExample checkInExample = new CheckInExample();
        List<CheckIn> list = checkInMapper.selectByExample(checkInExample);
        return list;
    }

    @Override
    public Date getCheckinDate(Integer roomId) {
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andRoomIdEqualTo(roomId).andIsCheckOutIsNull();
        Date checkin_date = checkInMapper.selectByExample(checkInExample).get(0).getCheckinDate();
        return checkin_date;
    }

    @Override
    public ArrayList<byte[]> faceList(int roomId) {
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andRoomIdEqualTo(roomId).andIsCheckOutIsNull();
        List<CheckIn> person = checkInMapper.selectByExample(checkInExample);
        ArrayList<byte[]> faceList = new ArrayList<>();
        for (CheckIn checkIn : person) {
            //faceList.add(checkIn.getFace());
        }
        return faceList;
    }


    @Override
    public ArrayList<byte[]> faceList() {
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andIsCheckOutIsNull();
        List<CheckIn> person = checkInMapper.selectByExample(checkInExample);
        ArrayList<byte[]> faceList = new ArrayList<>();
        for (CheckIn checkIn : person) {
            //faceList.add(checkIn.getFace());
        }
        return faceList;
    }

    @Override
    public int addCheckin(CheckIn checkIn) {
        int re = checkInMapper.insertSelective(checkIn);
        return re;
    }

    @Override
    public CheckIn getById(int id) {
        return checkInMapper.selectByPrimaryKey(id);
    }

    @Override
    public int is_check_out(int id) {
        CheckIn checkIn = new CheckIn();
        checkIn.setId(id);
        checkIn.setIsCheckOut(Byte.valueOf("1"));
        int re = checkInMapper.updateByPrimaryKeySelective(checkIn);
        return re;
    }

    @Override
    public CheckIn getByRoomId(int room_id) {
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andRoomIdEqualTo(room_id).andIsCheckOutIsNull();
        CheckIn checkIn = checkInMapper.selectByExample(checkInExample).get(0);
        return checkIn;
    }
}
