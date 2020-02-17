package com.arcsoft.hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DaysUtil {
    //计算时间差，以天数为单位。如：2018-08-08 和 2018-08-05 相差3天
    public int getDistanceTime(Date startTime, Date endTime) {
        int days = 0;
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();

        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = (int) (diff / (24 * 60 * 60 * 1000));
        return days;
    }

    //String转Date
    public Date StringtoDate(String ss) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Date date=sdf.parse(new Date().toString());
            Date date = sdf.parse(ss);
            //System.out.println(date);
            return date;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }


    //初始化日期中的时间 将时间变为00：00：00   转换时差 UTC
    public Date initialTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date1 = sdf.parse(sdf.format(date));
        return date1;
    }
}
