package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.CheckIn;

import java.text.ParseException;

public interface WxLoginService {
    CheckIn wxLogin(String path, String enginePath);
    JSONObject getUserWXLoginInfo(String wxCode);

    String login(String wxCode) throws ParseException;
}
