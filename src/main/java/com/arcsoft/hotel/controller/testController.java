package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.mapper.UserReserveMapper;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample;
import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.pojo.UserReserveExample;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.impl.checkinServiceImpl;
import com.arcsoft.hotel.service.roomService;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    checkinService checkinService;
    @Autowired
    roomService roomService;
    @Autowired
    UserReserveMapper userReserveMapper;

    @RequestMapping("/test1")
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
    @RequestMapping("/test2")
    public ArrayList<Date> t() throws ParseException {
        UserReserveExample userReserveExample = new UserReserveExample();
        Date date1 = userReserveMapper.selectByExampleWithBLOBs(userReserveExample).get(0).getCheckinDate();
        Date date2 = new Date();
        ArrayList<Date> list = new ArrayList<>();
        list.add(date1);
        list.add(date2);
        DaysUtil daysUtil = new DaysUtil();
        Date date3 = daysUtil.initialTime(date2);
        list.add(date3);
        Instant instant = Instant.now();
        System.out.println(instant);
        //Date date3=daysUtil.StringtoDate(instant.toString());
        return list;
    }
}
