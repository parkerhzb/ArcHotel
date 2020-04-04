package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.*;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class inviteController {
    @Autowired
    private userService userService;
    @Autowired
    private invitationService invitationService;
    @Autowired
    private visitorService visitorService;
    @Autowired
    private inpersonService inpersonService;
    @Autowired
    private roomService roomService;
    @Autowired
    private WxLoginService wxLoginService;

    @RequestMapping("/wIvtInfo")
    public JSONObject ivtInfo(@RequestParam("sess_id") String sess_id) {
        JSONObject result = new JSONObject(true);
        JSONArray invitationArr = new JSONArray();
        JSONArray applicationArr = new JSONArray();
        User user = userService.getBySid(sess_id);
        if (user == null) {
            result.put("error", "会话出错");
            return result;
        }
        try {
            //获取用户所在房间的所有邀请记录
            int roomId = invitationService.getRoomidByUser(user);
            if (roomId == -1) {
                result.clear();
                result.put("error", "获取用户住房信息失败");
                return result;
            }
            List<Invitation> invitations = invitationService.getByRoomid(roomId);
            if (invitations != null && invitations.size() != 0) {
                int ivtNum = 0;
                for (Invitation invitation : invitations) {
                    JSONObject ivt = new JSONObject(true);
                    ivt.put("ivtIdx", ivtNum);
                    ivtNum++;
                    ivt.put("ivtId", invitation.getId());
                    ivt.put("isValid", DaysUtil.isUnder24h(invitation.getTime(), new Date()));
                    ivt.put("ivtName", invitation.getName());
                    JSONArray visitorArr = new JSONArray();
                    int visitorNum = 0;
                    //获得每个邀请对应的所有访客信息
                    List<Visitor> visitors = visitorService.getByIvtId(invitation.getId());
                    if (visitors != null && visitors.size() != 0) {
                        for (Visitor visitor : visitors) {
                            JSONObject visitor_js = new JSONObject(true);
                            visitor_js.put("visitorIdx", visitorNum);
                            visitor_js.put("visitorId", visitor.getId());
                            visitor_js.put("visitorName", visitor.getName());
                            visitor_js.put("faceUrl", visitor.getFaceurl());
                            JSONObject perms = visitorService.getPermsByPerm(visitor.getPower());
                            visitor_js.put("perms", perms);
                            visitorNum++;
                            visitorArr.add(visitor_js);
                        }
                    }
                    ivt.put("visitorNum", visitorNum);
                    ivt.put("visitors", visitorArr);
                    User creator_user = userService.getById(invitation.getUserId());
                    if (creator_user == null) {
                        result.clear();
                        result.put("error", "获取创建人失败");
                        return result;
                    }
                    Inperson creator = inpersonService.getByPhoneNumber(creator_user.getPhoneNumber());
                    if (creator == null) {
                        result.clear();
                        result.put("error", "获取创建人失败");
                        return result;
                    }
                    ivt.put("ivtCreator", creator.getName());
                    Room room = roomService.getRoombyId(invitation.getRoomId());
                    if (room == null) {
                        result.clear();
                        result.put("error", "获取房间信息失败");
                        return result;
                    }
                    ivt.put("ivtPlace", room.getRoomNumber());
                    DaysUtil daysUtil = new DaysUtil();
                    ivt.put("ivtStart", daysUtil.Date2String3(invitation.getTime()));
                    ivt.put("ivtEnd", daysUtil.Date2String3(DaysUtil.after24h(invitation.getTime())));
                    invitationArr.add(ivt);
                }
            }

            //获取“我”作为访客的所有邀请信息
            List<Invitation> applications = invitationService.getByIdIn(visitorService.getIvtidByPhone(user.getPhoneNumber()));
            if (applications != null && applications.size() != 0) {
                for (Invitation application : applications) {
                    JSONObject apl = new JSONObject(true);
                    apl.put("isValid", DaysUtil.isUnder24h(application.getTime(), new Date()));
                    apl.put("ivtName", application.getName());
                    Visitor visitor = visitorService.getByIvtidAndPhone(application.getId(), user.getPhoneNumber());
                    if (visitor == null) {
                        result.clear();
                        result.put("error", "获取我的访问信息失败");
                        return result;
                    }
                    JSONObject perm = visitorService.getPermsByPerm(visitor.getPower());
                    apl.put("myPerms", perm);
                    User creator_user = userService.getById(application.getUserId());
                    if (creator_user == null) {
                        result.clear();
                        result.put("error", "获取我的创建人失败");
                        return result;
                    }
                    Inperson creator = inpersonService.getByPhoneNumber(creator_user.getPhoneNumber());
                    if (creator == null) {
                        result.clear();
                        result.put("error", "获取我的创建人失败");
                        return result;
                    }
                    apl.put("ivtCreator", creator.getName());
                    Room room = roomService.getRoombyId(application.getRoomId());
                    if (room == null) {
                        result.clear();
                        result.put("error", "获取房间信息失败");
                        return result;
                    }
                    apl.put("ivtPlace", room.getRoomNumber());
                    DaysUtil daysUtil = new DaysUtil();
                    apl.put("ivtStart", daysUtil.Date2String3(application.getTime()));
                    apl.put("ivtEnd", daysUtil.Date2String3(DaysUtil.after24h(application.getTime())));
                    applicationArr.add(apl);
                }
            }
        } catch (ParseException pe) {
            result.clear();
            result.put("error", "日期格式错误");
            return result;
        }
        result.put("myInvitations", invitationArr);
        result.put("myApplications", applicationArr);

        return result;
    }

    @RequestMapping("/wcrtIvt")
    public JSONObject crtIvt(@RequestParam("sess_id") String sess_id,
                             @RequestParam("name") String name, @RequestParam("dftPerm") JSONObject perm) {
        JSONObject result = new JSONObject(true);
        User user = userService.getBySid(sess_id);
        if (user == null) {
            result.put("error", "会话出错");
            return result;
        }
        int roomId = invitationService.getRoomidByUser(user);
        if (roomId == -1) {
            result.clear();
            result.put("error", "获取用户住房信息失败");
            return result;
        }
        Invitation invitation = new Invitation();
        invitation.setName(name);
        invitation.setRoomId(roomId);
        invitation.setUserId(user.getId());
        int lift = perm.getIntValue("lift");
        int room = perm.getIntValue("room");
        int permValue = lift * 1 + room * 2;
        invitation.setPower(String.valueOf(permValue));
        invitation.setTime(new Date());
        UUID uuid = UUID.randomUUID();
        String uuid_str = uuid.toString().replace("-", "");
        invitation.setInviteCode(uuid_str);
        boolean ok = invitationService.insertInvitation(invitation);
        if (!ok) {
            result.clear();
            result.put("error", "插入邀请信息失败");
            return result;
        }
        result.put("url", wxLoginService.getWxacode(sess_id, uuid_str));
        result.put("ivtCode", uuid_str);
        return result;
    }
}
