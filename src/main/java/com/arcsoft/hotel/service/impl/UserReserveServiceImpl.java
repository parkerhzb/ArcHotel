package com.arcsoft.hotel.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.mapper.MeetingDateMapper;
import com.arcsoft.hotel.mapper.MeetingReserveMapper;
import com.arcsoft.hotel.mapper.RoomTypeMapper;
import com.arcsoft.hotel.mapper.UserReserveMapper;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.roomService;
import com.arcsoft.hotel.service.roomTypeService;
import com.arcsoft.hotel.service.userReserveService;
import com.arcsoft.hotel.service.userService;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.sql.Timestamp;

@Service
public class UserReserveServiceImpl implements userReserveService {

    @Autowired
    private UserReserveMapper userReserveMapper;
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private MeetingReserveMapper meetingReserveMapper;
    @Autowired
    private MeetingDateMapper meetingDateMapper;
    @Autowired
    private roomService roomService;
    @Autowired
    private roomTypeService roomTypeService;

    @Override
    public JSONObject reserveRoom(String name, String phone, String roomType, int num, String checkIn, String checkOut, byte[] face, int user_id) throws ParseException {
        JSONObject json = new JSONObject();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (checkIn != null && checkOut != null) {
            Date checkInDate = new DaysUtil().Timestamp2Date(checkIn);
            Date checkOutDate = new DaysUtil().Timestamp2Date(checkOut);
            UserReserve userReserve = new UserReserve();
            userReserve.setName(name);
            userReserve.setPhoneNumber(phone);
            userReserve.setRoomType(roomType);
            userReserve.setNum(num);
            userReserve.setCheckinDate(new DaysUtil().initialTime(checkInDate));
            userReserve.setCheckoutDate(new DaysUtil().initialTime(checkOutDate));
            userReserve.setTime(time);
            userReserve.setFace(face);
            userReserve.setUserId(user_id);
            if (userReserveMapper.insertSelective(userReserve) > 0) {
                json.put("ok", 1);
            } else
                json.put("ok", 0);
        } else
            json.put("ok", -1);
        return json;
    }

    @Override
    public JSONObject reserveMeeting(String name, String phone, String roomType, JSONArray meetingPeriod, byte[] face, int user_id) throws ParseException {
        JSONObject json = new JSONObject();
        MeetingReserve meetingReserve = new MeetingReserve();
        meetingReserve.setName(name);
        meetingReserve.setPhoneNumber(phone);
        meetingReserve.setMeetingType(roomType);
        meetingReserve.setFace(face);
        meetingReserve.setUserId(user_id);
        meetingReserve.setStatus(1);    //标记为预定状态
        String code = new RandomUtil().getInvitecode();
        meetingReserve.setInviteCode(code);
        System.out.println(meetingReserve.toString());
        int result = meetingReserveMapper.insertSelective(meetingReserve);
        if (result == 0) {
            json.put("rsvSuc", 0);
            System.out.println("插入预定失败");
            return json;
        }
        int id = meetingReserve.getId();
        for (int i = 0; i < meetingPeriod.size(); i++) {
            MeetingDate meetingDate = new MeetingDate();
            meetingDate.setReserveId(id);
            JSONObject date_js = meetingPeriod.getJSONObject(i);
            Iterator iterator = date_js.keySet().iterator();
            if (iterator.hasNext()) {
                String date_str = iterator.next().toString();
                Date date = new DaysUtil().Timestamp2Date(date_str);
                meetingDate.setDate(new DaysUtil().initialTime(date));
                JSONObject period_js = date_js.getJSONObject(date_str);
                int morning = period_js.getIntValue("m");
                int afternoon = period_js.getIntValue("a");
                int evening = period_js.getIntValue("e");
                int period = 0;
                if (morning == 1) period += 1;
                if (afternoon == 1) period += 2;
                if (evening == 1) period += 4;
                meetingDate.setTimeperiod(period);
                int rs = meetingDateMapper.insertSelective(meetingDate);
                if (rs > 0) {
                    json.put("rsvSuc", 1);
                } else {
                    json.put("rsvSuc", 0);
                    System.out.println("插入date失败");
                }
            }

        }
        return json;
    }

    @Override
    public Map<String, Integer> ConflictItem(Date start, Date end) {
        //初始化房型与数量，初始值为0
        Map<String, Integer> list = new HashMap<>();
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        List<RoomType> roomTypes = roomTypeMapper.selectByExample(roomTypeExample);
        for (RoomType roomType : roomTypes) {
            list.put(roomType.getName(), 0);
        }
        //已经预定中冲突的房间
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andCheckinDateBetween(start, end);
        userReserveExample.or().andCheckoutDateBetween(start, end);
        List<UserReserve> userReserves = userReserveMapper.selectByExampleWithBLOBs(userReserveExample);
        for (UserReserve userReserve : userReserves) {
            String type = userReserve.getRoomType();
            int oldValue = list.get(type);
            list.replace(type, ++oldValue);
        }
        return list;
    }

    @Override
    public List<UserReserve> getReserve(java.util.Date now) {
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andCheckinDateLessThanOrEqualTo(now).andCheckoutDateGreaterThan(now);
        List<UserReserve> list = userReserveMapper.selectByExampleWithBLOBs(userReserveExample);
        return list;
    }

    @Override
    public int delItem(int id) {
        int re = roomTypeMapper.deleteByPrimaryKey(id);
        return re;
    }

    @Override
    public List getAll(int user_id) {
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andUserIdEqualTo(user_id);
        return userReserveMapper.selectByExample(userReserveExample);
    }

    @Override
    public int getNewNum(int user_id) {
        UserReserveExample userReserveExample = new UserReserveExample();
        userReserveExample.createCriteria().andUserIdEqualTo(user_id);
        userReserveExample.createCriteria().andCheckinDateGreaterThan(new Date());
        return userReserveMapper.countByExample(userReserveExample);
    }

    @Override
    public JSONObject reserveRoomInfo(Date start, Date end) {
        JSONObject result = new JSONObject(true);
        JSONArray room_list = new JSONArray();
        Map<String, Integer> checkinConflict = roomService.ConflictTypeFromCheckin(start, end);
        Map<String, Integer> reserveConfilect = ConflictItem(start, end);
        List<RoomType> roomTypes = roomTypeService.getAll();
        for (RoomType roomType : roomTypes) {
            JSONObject json = new JSONObject(true);
            json.put("type", 0);
            String name = roomType.getName();
            json.put("room_type", name);
            json.put("price", roomType.getPrice());
            String url = "https://drcxs.cn/images/type_ID_.jpg";
            url.replaceAll("_ID_", roomType.getId().toString());
            json.put("img", url);
            int num = roomType.getNum() - checkinConflict.get(name) - reserveConfilect.get(name);
            if (num < 0) num = 0;
            json.put("num", num);
            room_list.add(json);
        }
        result.put("room_list", room_list);
        return null;
    }
}
