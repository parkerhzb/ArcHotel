package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.Invitation;
import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.service.faceService;
import com.arcsoft.hotel.service.invitationService;
import com.arcsoft.hotel.service.userService;
import com.arcsoft.hotel.service.visitorService;
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

@RestController
public class visitorController {
    @Autowired
    private faceService faceService;
    @Autowired
    private visitorService visitorService;
    @Autowired
    private invitationService invitationService;
    @Autowired
    private userService userService;

    @RequestMapping("/waddvisitor")
    public JSONObject addVisitor(@RequestParam("name") String name, @RequestParam("phone") String phone,
                                 @RequestPart("face") MultipartFile facefile,
                                 @RequestParam("ivtCode") String ivtCode, @RequestParam("docType") Integer docType,
                                 @RequestParam("docNum") String docNumber) throws IOException {
        JSONObject result = new JSONObject();
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath, "visitor", ivtCode + "_" + phone);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
        //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        if (faceService.hasFace(filePath, enginePath)) {
            faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
            byte[] face = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData();
            Visitor visitor = new Visitor();
            visitor.setName(name);
            visitor.setPhoneNumber(phone);
            visitor.setFace(face);
            visitor.setFaceurl(filePath);
            Invitation invitation = invitationService.getByIvtcode(ivtCode);
            if (invitation == null) {
                result.clear();
                result.put("error", "该邀请不存在");
                return result;
            }
            visitor.setPower(invitation.getPower());
            visitor.setDocumentType(docType);
            visitor.setDocumentNumber(docNumber);
            int re = visitorService.insertVisitor(visitor);
            if (re > 0) {
                result.put("isSuc", 1);
            } else {
                result.put("isSuc", 0);
                result.put("error", "数据库插入错误！");
            }
        } else {
            result.put("isSuc", 0);
            result.put("error", "未检测到人脸信息！");
        }
        return result;
    }

    @RequestMapping("/wPerm")
    public JSONObject addVisitor(@RequestParam("sess_id") String sess_id,
                                 @RequestParam("ivtCode") String ivtCode,
                                 @RequestParam("visitor_id") Integer visitor_id,
                                 @RequestParam("perm") JSONObject perm) throws IOException {
        JSONObject result = new JSONObject(true);
        User user = userService.getBySid(sess_id);
        if (user == null) {
            result.put("isSuc", 0);
            result.put("error", "会话出错");
            return result;
        }
        int lift = perm.getIntValue("lift");
        int room = perm.getIntValue("room");
        int permValue = 1 * lift + 2 * room;
        Visitor visitor = new Visitor();
        visitor.setId(visitor_id);
        visitor.setPower(String.valueOf(permValue));
        if (visitorService.update(visitor)) {
            result.put("isSuc", 1);
        } else {
            result.put("isSuc", 0);
            result.put("error", "插入数据库失败");
        }
        return result;
    }
}
