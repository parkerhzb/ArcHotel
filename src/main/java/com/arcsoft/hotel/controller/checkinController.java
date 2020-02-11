package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample;
import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.impl.checkinServiceImpl;
import com.arcsoft.hotel.service.roomService;
import com.arcsoft.hotel.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class checkinController {

    @Autowired
    checkinService checkinService;
    @Autowired
    roomService roomService;

    @RequestMapping("/checkin")
    public String checkinAll(Model model, @RequestParam("num") int num) throws IOException {
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
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
