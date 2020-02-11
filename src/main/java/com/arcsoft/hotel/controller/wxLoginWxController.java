package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class wxLoginWxController {

    @Autowired
    WxLoginService wxLoginService;

    @RequestMapping("/wxlogin")
    public JSONObject wxLoginWx(@RequestParam("wxCode") String wxCode) {
        JSONObject json = new JSONObject();
        try {
            String session_id = wxLoginService.login(wxCode);
            json.put("session_id", session_id);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", 1);
        }
        return json;
    }
}
