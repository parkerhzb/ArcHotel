package com.arcsoft.hotel.util;

import java.util.Date;

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
}
