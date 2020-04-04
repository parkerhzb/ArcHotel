package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.mapper.RoomMapper;
import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class roomServiceImpl implements roomService {
    @Autowired
    RoomMapper roomMapper;

    @Autowired
    CheckInMapper checkInMapper;

    @Autowired
    RoomTypeMapper roomTypeMapper;
    @Override
    public Room getRoombyId(Integer Id) {
        Room room = roomMapper.selectByPrimaryKey(Id);
        return room;
    }

    @Override
    public Map<String, Integer> ConflictTypeFromCheckin(Date start, Date end) {
        List<Integer> roomIds = new ArrayList<>();
        List<Integer> typeIds = new ArrayList<>();

        Map<String, Integer> list = new HashMap<>();
        //初始化房型与数量,初始化值为0
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        List<RoomType> roomTypes = roomTypeMapper.selectByExample(roomTypeExample);
        for (RoomType roomType : roomTypes) {
            list.put(roomType.getName(), 0);
        }
        //checkin中退房时间大于start的入住记录
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andCheckoutDateGreaterThan(start);
        List<CheckIn> checkIns = checkInMapper.selectByExample(checkInExample);
        for (CheckIn checkIn : checkIns) {
            roomIds.add(checkIn.getRoomId());
        }
        //冲突的房间类型
        if (roomIds.size() != 0) {
            RoomExample roomExample = new RoomExample();
            roomExample.createCriteria().andIdIn(roomIds);
            List<Room> rooms = roomMapper.selectByExample(roomExample);
            for (Room room : rooms) {
                typeIds.add(room.getTypeId());
            }
            roomTypeExample.createCriteria().andIdIn(typeIds);
            roomTypes = roomTypeMapper.selectByExample(roomTypeExample);
            for (RoomType roomType : roomTypes) {
                String type = roomType.getName();
                int oldValue = list.get(type);
                list.replace(type, ++oldValue);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Integer> ConflictRoomFromCheckin(Date start, Date end) {
        ArrayList<Integer> roomIds = new ArrayList<>();

        //checkin中退房时间大于start的入住记录
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andCheckoutDateGreaterThan(start);
        List<CheckIn> checkIns = checkInMapper.selectByExample(checkInExample);
        for (CheckIn checkIn : checkIns) {
            roomIds.add(checkIn.getRoomId());
        }
        return roomIds;
    }

    @Override
    public Map<String, Integer> EmptyTypeNum() {
        Map<String, Integer> list = new HashMap<>();
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        List<RoomType> roomTypes = roomTypeMapper.selectByExample(roomTypeExample);
        for (RoomType roomType : roomTypes) {
            RoomExample roomExample = new RoomExample();
            roomExample.createCriteria().andStateEqualTo("0").andTypeIdEqualTo(roomType.getId());
            list.put(roomType.getName(), roomMapper.countByExample(roomExample));
        }
        return list;
    }


    @Override
    public List<Room> getAll() {
        RoomExample roomExample = new RoomExample();
        List<Room> list = roomMapper.selectByExample(roomExample);
        return list;
    }

    @Override
    public List<Room> EmptyRoombyTypeId(int typeId) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andStateEqualTo("0").andTypeIdEqualTo(typeId);
        List<Room> list = roomMapper.selectByExample(roomExample);
        return list;
    }

    @Override
    public int getTypeNum(int typeId) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andTypeIdEqualTo(typeId);
        int num = roomMapper.countByExample(roomExample);
        return num;
    }

    @Override
    public String getRoomNum(int id) {
        Room room = roomMapper.selectByPrimaryKey(id);
        if (room == null)
            return null;
        return room.getRoomNumber();
    }

    @Override
    public Room getRoomByNumber(String room_number) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andRoomNumberEqualTo(room_number);
        Room room = roomMapper.selectByExample(roomExample).get(0);
        return room;
    }
}
