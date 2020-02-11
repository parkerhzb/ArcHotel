package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.mapper.UserMapper;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.WxLoginService;
import com.arcsoft.hotel.util.UrlUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.text.ParseException;
import java.util.*;

@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    CheckInMapper checkInMapper;
    CheckInExample checkInExample = new CheckInExample();

    @Autowired
    private UserMapper userMapper;
    private UserExample userExample = new UserExample();

    private static int no = 0;

    /**
     * 实现微信小程序扫脸登录功能
     *
     * @param path 图片文件存储路径
     * @return
     */
    @Override
    public CheckIn wxLogin(String path, String enginePath) {
        CheckIn checkInEntity = new CheckIn();
        File file = new File(path);
        no++;
        //查询check_in未退房的所有访客
        checkInExample.createCriteria().andIsCheckOutIsNull();
        List<CheckIn> checkIns = checkInMapper.selectByExampleWithBLOBs(checkInExample);
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        //获取用户特征值
        byte[] face = faceRecUtil.getFaceFeature(file).getFeatureData();
        //对比
        if (checkIns.size() != 0) {
            int flag = 0;
            for (CheckIn checkIn : checkIns) {
                if (faceRecUtil.getFaceSimilar(face, checkIn.getFace()) > 0.8) {
                    flag = 1;
                    checkInEntity = checkIn;
                    break;
                }
            }
            if (flag == 0) {
                checkInEntity = null;
            }
        }
        faceRecUtil.unInitEngine();
        return checkInEntity;
    }

    @Override
    public JSONObject getUserWXLoginInfo(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid", "wx4fe6eb14b8f6082dAppSecret");    //开发者设置中的appId
        requestUrlParam.put("secret", "254f54ecfea87343a15012690d1fccf7");    //开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode);    //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    /*
    获取用户wx信息
     */
    @Override
    public String login(String wxCode) throws ParseException {
        //请求微信api获取用户的openid和sessionKey
        JSONObject jsonObject = getUserWXLoginInfo(wxCode);
        if (jsonObject != null && !jsonObject.containsKey("openid")) {
            System.out.println("获取openid失败");
            return "error";
        }
        String openid = (String) jsonObject.get("openid");
        String sessionKey = (String) jsonObject.get("session_key");

        //通过openid查询数据库是否有此用户
        userExample.createCriteria().andOpenIdEqualTo(openid);
        if (userMapper.selectByExample(userExample) == null) {//用户不存在
            User user = new User();
            user.setOpenId(openid);
            int success = userMapper.insertSelective(user);
            if (success < 1) {
                System.out.println("插入用户openid失败！");
                return "error";
            }
        }
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        UUID uuid = UUID.randomUUID();
        String uuid_str = uuid.toString();
        jedis.hmget(uuid_str, "openid", openid, "session_key", sessionKey);
        jedis.expire(uuid_str, 60 * 60);
        jedis.close();
//        jsonObject.put("userId", newUser.get(0).getUserId());
//        jsonObject.put("dateTime",DatetimeUtil.dateToStamp(new Date()));
//        return Common.getReturn(jsonObject);
        return uuid_str;
    }

}
