package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.service.userReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*预定功能

 */
@RestController
public class reserveController {

    @Autowired
    userReserveService userReserveService;

    @RequestMapping("/reserve")
    public JSONObject reserve(@RequestParam("reserveType") String rsvType, @RequestParam("roomType") String roomType,
                              @RequestParam("rsvName") String name, @RequestParam("rsvPhone") String phone,
                              @RequestParam("rsvRoomNum") String numStr, @RequestParam("checkInTime") String CheckIn,
                              @RequestParam("checkOutTime") String CheckOut) {

        JSONObject result = new JSONObject();
        //住房预定
        if (rsvType.equals("0")) {
            int num = -1;
            if (numStr == null || numStr.equals("")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
                return result;
            }
            num = Integer.parseInt(numStr.trim());
            JSONObject isSuccess = userReserveService.reserveRoom(name, phone, roomType, num, CheckIn, CheckOut);
            if (isSuccess.get("ok").equals("-1")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
            } else if (isSuccess.get("ok").equals("0")) {
                result.put("success", 0);
                result.put("error", "预定失败！");
            } else {
                result.put("success", 1);
            }
            return result;

        }
        //会议室预订
        else if (rsvType.equals("1")) {

            return result;
        }
        return result;
    }
}
