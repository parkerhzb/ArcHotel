package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.mapper.CarMapper;
import com.arcsoft.hotel.pojo.Car;
import com.arcsoft.hotel.pojo.CarExample;
import com.arcsoft.hotel.service.carService;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.smartcardio.CardException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {

//    @Autowired
//    checkinService checkinService;
//    @Autowired
//    roomService roomService;
//    @Autowired
//    UserReserveMapper userReserveMapper;
//
//    @RequestMapping("/test1")
//    public String checkinAll(Model model, @RequestParam("num") int num) throws IOException {
//        Resource resource = new ClassPathResource("");
//        FileUploadUtil fileUploadUtil = new FileUploadUtil();
//        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
////        checkInExample.createCriteria().andIsCheckOutIsNull();
////        List<CheckIn> checkIns=checkInMapper.selectByExampleWithBLOBs(checkInExample);
//        Room room = roomService.getRoombyId(1);
//        List<CheckIn> checkIns = checkinService.getAll();
//        //System.out.println(checkIns.get(1).getCheckin_Date());
//        model.addAttribute("checkin", checkIns);
//        System.out.println(num);
//        return String.valueOf(num);
//    }
//    @RequestMapping("/test2")
//    public ArrayList<Date> t() throws ParseException {
//        UserReserveExample userReserveExample = new UserReserveExample();
//        Date date1 = userReserveMapper.selectByExampleWithBLOBs(userReserveExample).get(0).getCheckinDate();
//        Date date2 = new Date();
//        ArrayList<Date> list = new ArrayList<>();
//        list.add(date1);
//        list.add(date2);
//        DaysUtil daysUtil = new DaysUtil();
//        Date date3 = daysUtil.initialTime(date2);
//        list.add(date3);
//        Instant instant = Instant.now();
//        System.out.println(instant);
//        //Date date3=daysUtil.StringtoDate(instant.toString());
//        return list;
//    }
//    @RequestMapping(value = "/test3", produces = "application/json;charset=UTF-8")
//    public void t3(@RequestPart("meetingPeriod") JSONObject meetingPeriod,@RequestBody MultipartFile face){
//        System.out.println(face);
//        System.out.println(meetingPeriod);
//        JSONArray array=meetingPeriod.getJSONArray("meetingPeriod");
//        System.out.println(array);
//        JSONObject json1=array.getJSONObject(0);
//        System.out.println(json1.get("a"));
//    }

    @RequestMapping("/facetest")
    public String facetest(@RequestParam("face") MultipartFile face) throws IOException {
        String re = "!!!";
        try {
            //获取人脸图片
            Resource resource = new ClassPathResource("");
            FileUploadUtil fileUploadUtil = new FileUploadUtil();
            String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
            String filePath = fileUploadUtil.fileUpload(face, OriginPath, "test", new DaysUtil().DatetoString(new Date()) + ".jpg");
            String enginePath = OriginPath + File.separator + "lib" + File.separator + "LINUX64";
            //String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
            enginePath = enginePath.replaceAll("\\\\", "/");
            faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
            int length = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData().length;
            faceRecUtil.unInitEngine();
            re = String.valueOf(length);
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            return re + "\nMessage:::" + e.getMessage() + "\nLocalizedMessage:::" + e.getLocalizedMessage() + "\nStackTrace:::" + e.getStackTrace() +
                    "\nCause:::" + e.getCause();
        }

    }

    @Autowired
    carService carService;

    @RequestMapping("/facetest2")
    public String facetest2() throws IOException {
        Resource resource = new ClassPathResource("");
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes
        //String enginePath=OriginPath+File.separator+"lib"+File.separator+"LINUX64";
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        List<Car> cars = carService.getByFlag(1);
        if (cars.size() == 0)
            return "kong";
        byte[] face = cars.get(0).getFace();
        File file = new File("C:\\Users\\Parker\\Desktop\\picture\\1.jpg");
        double sim = faceRecUtil.getFaceSimilar(file, face);
        return String.valueOf(sim);
    }
}
