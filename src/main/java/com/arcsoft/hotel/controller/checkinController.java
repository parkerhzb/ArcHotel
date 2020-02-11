package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample;
import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.impl.checkinServiceImpl;
import com.arcsoft.hotel.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class checkinController {

    @Autowired
    checkinService checkinService;
    @Autowired
    roomService roomService;

    @RequestMapping("/checkin")
    public String checkinAll(Model model, @RequestParam("num") int num) {
//        checkInExample.createCriteria().andIsCheckOutIsNull();
//        List<CheckIn> checkIns=checkInMapper.selectByExampleWithBLOBs(checkInExample);
        Room room = roomService.getRoombyId(1);
        List<CheckIn> checkIns = checkinService.getAll();
        //System.out.println(checkIns.get(1).getCheckin_Date());
        model.addAttribute("checkin", checkIns);
        System.out.println(num);
        return String.valueOf(num);
    }
}
