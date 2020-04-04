package com.arcsoft.hotel.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.arcsoft.hotel.mapper.CheckInMapper;
import com.arcsoft.hotel.mapper.MeetingReserveMapper;
import com.arcsoft.hotel.mapper.UserMapper;
import com.arcsoft.hotel.mapper.UserReserveMapper;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.*;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.UrlUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    CheckInMapper checkInMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private userService userService;
    @Autowired
    private userReserveService userReserveService;
    @Autowired
    private meetingReserveService meetingReserveService;
    @Autowired
    private com.arcsoft.hotel.service.roomService roomService;
    @Autowired
    private checkoutService checkoutService;
    @Autowired
    private inpersonService inpersonService;
    @Autowired
    private meetingDateService meetingDateService;
    @Autowired
    private meetingTypeService meetingTypeService;

    private static int no = 0;

    /**
     * 实现微信小程序扫脸登录功能
     *
     * @param path 图片文件存储路径
     * @return
     */
    @Override
    public Inperson wxLogin(String path, String enginePath, String sess_id) {
        Inperson inperson = new Inperson();
        File file = new File(path);
        no++;
        //查询check_in未退房的所有访客
        CheckInExample checkInExample = new CheckInExample();
        checkInExample.createCriteria().andIsCheckOutIsNull();
        List<CheckIn> checkIns = checkInMapper.selectByExample(checkInExample);
        List<Inperson> inpeople = inpersonService.getAllin(checkIns);
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        //修改user表中的state
        UserExample userExample = new UserExample();
        userExample.createCriteria().andSsesIdEqualTo(sess_id);
        User user = new User();
        //获取用户特征值
        byte[] face = faceRecUtil.getFaceFeature(file).getFeatureData();
        //对比
        if (checkIns.size() != 0) {
            int flag = 0;
            for (Inperson inpersonEntity : inpeople) {
                if (faceRecUtil.getFaceSimilar(face, inpersonEntity.getFace()) > 0.8) {
                    flag = 1;
                    inperson = inpersonEntity;
                    user.setStatus("1");
                    //user.setInpersonId(inperson.getId());
                    userMapper.updateByExampleSelective(user, userExample);
                    break;
                }
            }
            if (flag == 0) {
                inperson = null;
            }
        }
        faceRecUtil.unInitEngine();
        return inperson;
    }

    public JSONObject historyOrder(String sess_id) {
        JSONObject result = new JSONObject(true);
        JSONObject details = new JSONObject(true);
        JSONArray detailsArr = new JSONArray();
        User user = userService.getBySid(sess_id);
        if (user == null) {
            result.put("error", "数据错误");
            return result;
        }
        int user_id = user.getId();
        List<UserReserve> rooms = userReserveService.getAll(user_id);
        List<MeetingReserve> meetings = meetingReserveService.getAll(user_id);
        if (rooms != null) {
            for (UserReserve userReserve : rooms) {
                JSONObject reserve = new JSONObject(true);
                reserve.put("type", 0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(userReserve.getCheckinDate());
                reserve.put("year", calendar.get(Calendar.YEAR));
                reserve.put("checkIn", calendar.get(Calendar.DATE));
                calendar.setTime(userReserve.getCheckoutDate());
                reserve.put("checkOut", calendar.get(Calendar.DATE));
                reserve.put("status", userReserve.getStatus());
                reserve.put("roomType", userReserve.getRoomType());
                reserve.put("amount", checkoutService.getConsumeByCheckinid(userReserve.getCheckinId()));
                detailsArr.add(reserve);
            }
        }
        if (meetings != null) {
            for (MeetingReserve meetingReserve : meetings) {
                JSONObject reserve = new JSONObject(true);
                reserve.put("type", 1);
                JSONObject datesAndPeriodNum = meetingDateService.getMyDateAndPeriod(meetingReserve.getId());
                int num = datesAndPeriodNum.getInteger("usedPeriodNum");
                if (num != 0) {
                    reserve.put("dates", datesAndPeriodNum.getJSONObject("dates"));
                }
                reserve.put("status", meetingReserve.getStatus());
                MeetingType meetingType = meetingTypeService.getByName(meetingReserve.getMeetingType());
                if (meetingType == null) {
                    result.clear();
                    ;
                    result.put("error", "数据错误");
                    return result;
                }
                reserve.put("meetingType", meetingType.getName());
                reserve.put("amount", meetingType.getPrice() * num);
                detailsArr.add(reserve);
            }
        }
        result.put("newOrderNum", userReserveService.getNewNum(user_id) + meetingReserveService.getNewNum(user_id));
        result.put("details", detailsArr);
        return result;
    }


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

    public JSONObject getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> requestUrlParam = new HashMap<String, Object>();
        requestUrlParam.put("grant_type", "client_credential");    //开发者设置中的appId
        requestUrlParam.put("appid", "wx4fe6eb14b8f6082dAppSecret");    //开发者设置中的appId
        requestUrlParam.put("secret", "254f54ecfea87343a15012690d1fccf7");    //开发者设置中的appSecret
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendGet(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public JSONObject getUserInfo(String openid, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo";
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("access_token", accessToken);
        requestUrlParam.put("openid", openid);
        requestUrlParam.put("lang", "zh_CN");
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }


    /*
    获取用户wx信息
     */
    @Override
    public JSONObject login(String wxCode) {
        //请求微信api获取用户的openid和sessionKey
        JSONObject jsonObject = getUserWXLoginInfo(wxCode);
        JSONObject result = new JSONObject();
        if (jsonObject != null && !jsonObject.containsKey("openid")) {
            System.out.println("获取openid失败");
            result.put("error", "获取openid失败");
            return result;
        }
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        String accessToken = jsonObject.getString("access_token");

        //通过openid查询数据库是否有此用户
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openid);
        List gotusers = userMapper.selectByExample(userExample);
        if (gotusers == null) {//用户不存在
            JSONObject userInfo = getUserInfo(openid, accessToken);
            String nickname = userInfo.getString("nickname");
            String url = userInfo.getString("headimgurl");
            UUID uuid = UUID.randomUUID();
            String uuid_str = uuid.toString().replace("-", "");
            User user = new User();
            user.setOpenId(openid);
            user.setSsesId(uuid_str);
            user.setNickname(nickname);
            user.setHeadimgurl(url);
            int success = userMapper.insertSelective(user);
            if (success < 1) {
                System.out.println("存储用户信息失败");
                result.put("error", "存储用户信息失败");
                return result;
            }
            result.put("sess_id", uuid_str);
            result.put("identity", "");
            //将sessionKey和accessToken存储到redis中
            //strRedisTemplate.opsForValue().set("hotel:SK:"+uuid_str,sessionKey);
            //strRedisTemplate.opsForValue().set("hotel:AT"+uuid_str,accessToken);
            return result;
        }
        User gotuser = (User) gotusers.get(0);
        result.put("sess_id", gotuser.getSsesId());
        result.put("identity", gotuser.getStatus().equals("1") ? 1 : 0);
        return result;
    }

    /**
     * 用sess_id换取信息
     *
     * @param sess_id
     * @return json格式数据
     */
    public JSONObject getIdentity(String sess_id) {
        JSONObject result = new JSONObject();
        //通过sessid查询用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andSsesIdEqualTo(sess_id);
        List gotusers = userMapper.selectByExample(userExample);
        if (gotusers == null) {
            result.put("error", "sid出错");
            return result;
        }
        User user = (User) gotusers.get(0);
        result.put("identity", user.getStatus().equals("1") ? 1 : 0);
        return result;
    }

    @Override
    public String getWxacode(String sess_id, String ivtCode) {
        JSONObject result = getAccessToken();
        String errcode = result.getString("errcode");
        if (!errcode.equals(0))
            return null;
        String accessToken = result.getString("access_token");
        String requestUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
        String filePath = new String();
        try {
            Resource resource = new ClassPathResource("");
            String OriginPath = resource.getFile().getAbsolutePath();
            filePath = OriginPath + File.separator + "upload" + File.separator + "wxacode";
            File uploadDir = new File(filePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            filePath += File.separator + ivtCode;
            System.out.println(filePath);
            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", "1234567890");
//            paramJson.put("page", "pages/index/index"); //小程序暂未发布我没有带page参数
            paramJson.put("width", 430);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File(filePath));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
