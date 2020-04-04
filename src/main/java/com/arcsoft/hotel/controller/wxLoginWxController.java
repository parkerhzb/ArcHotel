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
    public JSONObject wxLoginWx(@RequestParam("data") String code) {
        JSONObject json = new JSONObject();
        try {
            json = wxLoginService.login(code);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", "数据异常");
            return json;
        }
        return json;
    }

    @RequestMapping("/wgetInfobySid")
    public JSONObject getInfobySid(@RequestParam("sess_id") String sess_id) {
        JSONObject json = new JSONObject();
        try {
            json = wxLoginService.getIdentity(sess_id);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", "数据异常");
            return json;
        }
        return json;
    }
}
