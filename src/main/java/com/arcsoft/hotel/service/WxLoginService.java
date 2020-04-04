package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.Inperson;

import java.text.ParseException;

public interface WxLoginService {
    Inperson wxLogin(String path, String enginePath, String sess_id);

    /**
     * 获得当前登录用户的历史预定记录
     *
     * @param sess_id 会话id
     * @return
     */
    JSONObject historyOrder(String sess_id);

    JSONObject login(String wxCode);

    JSONObject getIdentity(String sess_id);

    /**
     * 获取小程序码
     *
     * @return
     */
    String getWxacode(String sess_id, String ivtCode);
}
