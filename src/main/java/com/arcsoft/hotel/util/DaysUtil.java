package com.arcsoft.hotel.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Date StringtoDate(String ss, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            //Date date=sdf.parse(new Date().toString());
            Date date = sdf.parse(ss);
            //System.out.println(date);
            return date;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * Date类型转字符串（20200101）
     *
     * @param date
     * @return
     */
    public String Date2String(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String s = sdf.format(date);
            return s;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * Date类型转字符串（2020-01-01）
     *
     * @param date
     * @return
     */
    public String Date2String2(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(date);
            return s;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * Date类型转字符串（2020-01-01 10:10:10）
     *
     * @param date
     * @return
     */
    public String Date2String3(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);
            return s;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * 2020/3/14
     * 以pattern形式 返回String类型             yyyy-MM-dd HH-mm-ss
     */
    public String DatetoString(Date date, String pattern) {
        String str_date = "";
        try {
            //date=initialTime(date);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            str_date = sdf.format(date);
            System.out.println(str_date);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return str_date;
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param begin
     * @param end
     * @return
     * @Description TODO
     * @author XuJD
     * @date 2017-4-1
     */
    public List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        result.add(begin);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
        while (begin.getTime() <= end.getTime()) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        result.add(end);
        return result;
    }


    //初始化日期中的时间 将时间变为00：00：00   转换时差 UTC
    public Date initialTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date1 = sdf.parse(sdf.format(date));
        return date1;
    }

    /**
     * 3/14
     * 将string格式的时间戳转化为date类型
     *
     * @param timeStamp
     * @return
     */
    public Date Timestamp2Date(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.valueOf(timeStamp);
        Date date = new Date();
        try {
            String s = format.format(time);
            date = format.parse(s);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 对json数组排序，
     *
     * @param jsonArr
     * @param sortKey 排序关键字
     * @param is_desc is_desc-false升序列  is_desc-true降序 (排序字段为字符串)
     * @return
     */
    public static String jsonArraySort(JSONArray jsonArr, String sortKey, boolean is_desc) {
        //存放排序结果json数组
        JSONArray sortedJsonArray = new JSONArray();
        //用于排序的list
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        //将参数json数组每一项取出，放入list
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        //快速排序，重写compare方法，完成按指定字段比较，完成排序
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            //排序字段
            private final String KEY_NAME = sortKey;

            //重写compare方法
            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                try {
                    valA = a.getString(KEY_NAME);
                    valB = b.getString(KEY_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //是升序还是降序
                if (is_desc) {
                    return -valA.compareTo(valB);
                } else {
                    return -valB.compareTo(valA);
                }

            }
        });
        //将排序后结果放入结果jsonArray
        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray.toString();
    }

    public static boolean isUnder24h(Date date1, Date date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(sdf.format(date1));
        Date end = sdf.parse(sdf.format(date2));
        long dif = end.getTime() - start.getTime();
        if (dif < 0)
            return false;
        double cmp = dif * 1.0 / (1000 * 60 * 60);
        if (cmp <= 24)
            return true;
        return false;
    }

    /**
     * 计算一个date经过24h后的时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date after24h(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date old = sdf.parse(sdf.format(date));
        long dif = old.getTime() + (1000 * 60 * 60 * 24);
        return new DaysUtil().Timestamp2Date(String.valueOf(dif));
    }

    /*
    2020/3/14
    返回String类型 yyyy-MM-dd HH-mm-ss
     */
    public String DatetoString(Date date) {
        String str_date = "";
        try {
            //date=initialTime(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            str_date = sdf.format(date);
            System.out.println(str_date);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return str_date;
    }

    /**
     * 2020/3/16
     * 返回小时
     */
    public int getHour(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            int hour = Integer.valueOf(sdf.format(date));
            return hour;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return 0;
        }
    }


}
