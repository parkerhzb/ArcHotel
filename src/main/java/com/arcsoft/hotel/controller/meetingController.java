package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.meetingDateService;
import com.arcsoft.hotel.service.meetingReserveService;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class meetingController {

    @Autowired
    com.arcsoft.hotel.service.meetingService meetingService;
    @Autowired
    meetingDateService meetingdateService;
    @Autowired
    meetingReserveService meetingreService;

    @Autowired
    com.arcsoft.hotel.service.meetingattendService meetingattendService;

    @Autowired
    com.arcsoft.hotel.service.userService userService;


    /**
     * 2020/3/15
     * android会议室门前，查询显示当前会议室的信息
     */
    @RequestMapping("/information")
    public JSONObject meetingINF(@RequestParam("meeting_id") int meeting_id) throws ParseException {
        JSONObject result = new JSONObject();
        JSONArray meetingINF = new JSONArray();
        String meeting_number = meetingService.getMeetById(meeting_id).getMeetingNumber();
        result.put("meetingNumber", meeting_number);
        //step1//获得今天使用会议室的所有预约id
        Date date = new DaysUtil().initialTime(new Date());
        List<MeetingDate> meetingDates = meetingdateService.getByDate(date);
        //step2//筛选
        for (MeetingDate meetingDate : meetingDates) {
            MeetingReserve meetingReserve = meetingreService.getById(meetingDate.getReserveId());
            int Sel_Meeting_id = meetingReserve.getMeetingId();
            String number = meetingService.getMeetById(Sel_Meeting_id).getMeetingNumber();
            if (number.equals(meeting_number)) {
                JSONObject object = new JSONObject();
                object.put("name", meetingReserve.getName());
                object.put("phone", meetingReserve.getPhoneNumber());
                int period = meetingDate.getTimeperiod();
                switch (period) {
                    case 1:
                        object.put("period", "上午场09：00--11：30");
                        meetingINF.add(object);
                        break;
                    case 2:
                        object.put("period", "下午场14：00--16：30");
                        meetingINF.add(object);
                        break;
                    case 4:
                        object.put("period", "晚上场19：00--21：30");
                        meetingINF.add(object);
                        break;
                    case 3:
                        object.put("period", "上午场09：00--11：30");
                        meetingINF.add(object);
                        object.replace("period", "下午场14：00--16：30");
                        meetingINF.add(object);
                        break;
                    case 5:
                        object.put("period", "上午场09：00--11：30");
                        meetingINF.add(object);
                        object.replace("period", "晚上场19：00--21：30");
                        meetingINF.add(object);
                        break;
                    case 6:
                        object.put("period", "下午场14：00--16：30");
                        meetingINF.add(object);
                        object.replace("period", "晚上场19：00--21：30");
                        meetingINF.add(object);
                        break;
                    case 7:
                        object.put("period", "上午场09：00--11：30");
                        meetingINF.add(object);
                        object.replace("period", "下午场14：00--16：30");
                        meetingINF.add(object);
                        object.replace("period", "晚上场19：00--21：30");
                        meetingINF.add(object);
                        break;
                }
            }
        }
        int INFnum = meetingINF.size();
        JSONObject nullINF = new JSONObject();
        nullINF.put("name", "空");
        nullINF.put("phone", "空");
        nullINF.put("period", "空");
        switch (INFnum) {
            case 0:
                meetingINF.add(nullINF);
            case 1:
                meetingINF.add(nullINF);
            case 2:
                meetingINF.add(nullINF);
            case 3:
                break;
        }
        result.put("information", meetingINF);
        return result;
    }

    /**
     * 2020/3/16
     * 会议人员前台扫脸签到
     */
    @RequestMapping("/sign")
    public JSONObject NmeetingSign(@RequestParam("facefile") MultipartFile facefile) throws IOException, ParseException {
        JSONObject result = new JSONObject();
        String msg1 = "请确认您是否加入会议，或是否已经签到";
        String msg2 = "签到成功，请尽快入场";
        String msg3 = "请检查参会日期与时间";
        String number = "空";

        Resource resource = new ClassPathResource("");
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        String facepath = new FileUploadUtil().fileUpload(facefile, OriginPath, "meeting", new DaysUtil().DatetoString(new Date()) + ".jpg");

        List<MeetingAttendees> meetingAttendeesList = meetingattendService.getByStatus();
        if (meetingAttendeesList.size() == 0) {
            new File(facepath).delete();
            result.put("msg", msg1);
            result.put("number", number);
            return result;
        }

        File face = new File(facepath);
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        for (MeetingAttendees meetingAttendees : meetingAttendeesList) {
            if (faceRecUtil.getFaceSimilar(face, meetingAttendees.getFace()) > 0.8) {
                List<MeetingDate> meetingDateList = meetingdateService.getByRe_id(meetingAttendees.getReserveId());
                for (MeetingDate meetingDate : meetingDateList) {
                    Date today = new DaysUtil().initialTime(new Date());
                    if (meetingDate.getDate().equals(today)) {//判断开会日期
                        int hour = new DaysUtil().getHour(today);
                        //判断开会时间
                        switch (meetingDate.getTimeperiod()) {
                            case 1://m
                                if (hour > 8 && hour < 11) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 2://a
                                if (hour > 13 && hour < 16) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 4://e
                                if (hour > 18 && hour < 21) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 3://m+a
                                if ((hour > 8 && hour < 11) || (hour > 13 && hour < 16)) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 5://m+e
                                if ((hour > 8 && hour < 11) || (hour > 18 && hour < 21)) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 6://a+e
                                if ((hour > 18 && hour < 21) || (hour > 13 && hour < 16)) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                            case 7://m+a+e
                                if ((hour > 8 && hour < 11) || (hour > 13 && hour < 16) || (hour > 18 && hour < 21)) {
                                    result.put("msg", msg2);
                                    int meeting_id = meetingreService.getById(meetingAttendees.getReserveId()).getMeetingId();
                                    result.put("number", meetingService.getMeetById(meeting_id).getMeetingNumber());
                                    return result;
                                } else {
                                    face.delete();
                                    result.put("msg", msg3);
                                    result.put("number", number);
                                    return result;
                                }
                        }
                    }
                }
            }
        }

        face.delete();
        result.put("msg", msg1);
        result.put("number", number);
        return result;
    }

    /**
     * 2020/3/16
     * 给会议室内的Android设备 返回签到情况
     */
    @RequestMapping("/signinf")
    public JSONArray signINF(@RequestParam("meeting_id") int meeting_id) throws ParseException {
        JSONArray result = new JSONArray();
        String meeting_number = meetingService.getMeetById(meeting_id).getMeetingNumber();

        Date date = new Date();
        DaysUtil daysUtil = new DaysUtil();
        int hour = daysUtil.getHour(date);
        date = daysUtil.initialTime(date);
        List<MeetingDate> meetingDates = new ArrayList<MeetingDate>();
        if (hour < 12) {//上午 返回包含上午场的所有场
            meetingDates = meetingdateService.getByDateANDHour(date, 1);
            meetingDates.addAll(meetingdateService.getByDateANDHour(date, 5));
            meetingDates.addAll(meetingdateService.getByDateANDHour(date, 7));
        } else if (hour < 18) {//下午 返回包含下午场的所有场
            meetingDates = meetingdateService.getByDateANDHour(date, 2);
            meetingDates.addAll(meetingdateService.getByDateANDHour(date, 6));
        } else { //晚上 返回晚上场
            meetingDates = meetingdateService.getByDateANDHour(date, 4);
        }

        int reserve_id = 0;
        for (MeetingDate meetingDate : meetingDates) {
            MeetingReserve meetingReserve = meetingreService.getById(meetingDate.getReserveId());
            int Sel_meeting_id = meetingReserve.getMeetingId();
            String number = meetingService.getMeetById(Sel_meeting_id).getMeetingNumber();
            if (number.equals(meeting_number)) {
                reserve_id = meetingDate.getReserveId();
                break;
            }
        }

        List<MeetingAttendees> attendeesList = meetingattendService.getByReserveId(reserve_id);
        for (MeetingAttendees attendees : attendeesList) {
            JSONObject object = new JSONObject();
            object.put("name", attendees.getName());
            object.put("status", attendees.getStatus());//0 未签到；1 签到
            result.add(object);
        }

        return result;
    }

    /**
     * 2020/3/21
     * 给小程序显示会议室类型与价格
     */
    @RequestMapping("/show")
    public JSONArray showInwx() {
        JSONArray result = new JSONArray();
        List<MeetingType> meetingTypeList = meetingService.getAllType();
        String image = "https://drcxs.cn/images/type";
        for (MeetingType meetingType : meetingTypeList) {
            JSONObject object = new JSONObject();
            object.put("type_id", meetingType.getId());
            object.put("url", image + meetingType.getId() + ".jpg");
            object.put("type_name", meetingType.getName());
            object.put("price", meetingType.getPrice());
            result.add(object);
        }
        return result;
    }

    /**
     * 2020/3/21
     * 根据会议室的预约信息，判断预约是否能够满足
     */
    @RequestMapping(value = "/remeetjudge", produces = "application/json;charset=UTF-8")
    public JSONObject judgeMeetingReserve(@RequestParam("date") JSONObject list, @RequestParam("sess_id") String sess_id, @RequestPart("face") MultipartFile face) throws IOException {
        JSONObject rsvSuc = new JSONObject();

        String meetingType = list.getString("meetingType");
        int type_id = meetingService.getTypeByName(meetingType).getId();
        JSONArray dates = list.getJSONArray("dates");
        DaysUtil daysUtil = new DaysUtil();

        //获得分配的会议室号
        int meeting_id = -1;
        int reNum = dates.size();
        if (reNum > 1) {//预约天数一天以上，选择空会议室直接占用
            List<Meeting> meetingList = meetingService.getFreeMeetByType(type_id, "0", false);
            if (meetingList.size() == 0) {//没有空闲会议室
                rsvSuc.put("sucess", 0);
                rsvSuc.put("msg", meetingType + "会议室不足");
                return rsvSuc;
            }
            //就近原则占用一间会议室
            meeting_id = meetingList.get(0).getId();
        } else {//预约天数一天，插空方式，选择会议室
            JSONObject item = dates.getJSONObject(0);
            int state = 0;
            if (item.getInteger("m") == 1)
                state += 1;
            if (item.getInteger("a") == 1)
                state += 2;
            if (item.getInteger("e") == 1)
                state += 4;
            List<Meeting> meetingList = meetingService.getFreeMeetByType(type_id, String.valueOf(state), true);
            if (meetingList.size() == 0) {//没有空闲会议室
                rsvSuc.put("sucess", 0);
                rsvSuc.put("msg", meetingType + "会议室不足");
                return rsvSuc;
            }
            //就近原则占用一间会议室
            meeting_id = meetingList.get(0).getId();
        }

        //获取人脸图片
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        String filePath = fileUploadUtil.fileUpload(face, OriginPath, "meeting", daysUtil.DatetoString(new Date()) + ".jpg");
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        byte[] faceFeatureDate = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData();
        faceRecUtil.unInitEngine();

        //在reserve中添加预约记录
        String name = list.getString("name");
        String phone = list.getString("phone");
        int usr_id = userService.getUsrByPhone(phone).getId();

        MeetingReserve meetingReserve = new MeetingReserve();
        meetingReserve.setMeetingType(meetingType);
        meetingReserve.setName(name);
        meetingReserve.setPhoneNumber(phone);
        meetingReserve.setFace(faceFeatureDate);
        meetingReserve.setUserId(usr_id);
        meetingReserve.setStatus(0);
        meetingReserve.setMeetingId(meeting_id);
        meetingreService.addReserve(meetingReserve);
        int reserve_id = meetingReserve.getId();

        //分别处理每一天的预约，加入meet date
        for (int i = 0; i < reNum; ++i) {
            JSONObject object = dates.getJSONObject(i);
            Date date = daysUtil.StringtoDate(object.getString("date"), "yyMMdd");
            int period = 0;
            if (object.getInteger("m") == 1)
                period += 1;
            if (object.getInteger("a") == 1)
                period += 2;
            if (object.getInteger("e") == 1)
                period += 4;
            MeetingDate meetingDate = new MeetingDate();
            meetingDate.setReserveId(reserve_id);
            meetingDate.setDate(date);
            meetingDate.setTimeperiod(period);
            meetingdateService.addMeetDate(meetingDate);
        }

        //添加负责人进入attendees
        MeetingAttendees meetingAttendees = new MeetingAttendees();
        meetingAttendees.setReserveId(reserve_id);
        meetingAttendees.setName(name);
        meetingAttendees.setFace(faceFeatureDate);
        meetingAttendees.setStatus(0);
        meetingattendService.addAttendees(meetingAttendees);

        rsvSuc.put("sucess", 1);
        rsvSuc.put("msg", meetingType + "会议室预约成功");
        return rsvSuc;
    }
}
