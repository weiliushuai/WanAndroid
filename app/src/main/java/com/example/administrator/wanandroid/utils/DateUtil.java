package com.example.administrator.wanandroid.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @t author：LC： 类描述：这个类是实现时间与时间戳的转换
 */

public class DateUtil {

    public static long DEFAULT_TIME = System.currentTimeMillis() - 1 * 24 * 3600 * 1000;

    /**
     * 将一段时间片段，转换成时间格式，00:00:00 比如视频，音乐，时间
     */
    public static String sometimeToDate(long sometime) {
        float sec = (float) sometime % 1000;
        long time = 0;
        if (sec < 500) {
            time = sometime / 1000;
        } else {
            time = sometime / 1000 + 1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        String text = sdf.format(new Date((time - 8 * 60 * 60) * 1000));// 为什么要减8个小时
        return text;
    }

    /**
     * 将年月日转换为 时间戳 例如 2015-7-12
     *
     * @param year_month_day
     * @return
     */
    public static String dateToStr(String year_month_day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
            Date d = sdf.parse(year_month_day);
            long unixTimestamp = d.getTime() / 1000;
            Log.i("time", "dateToStr--------->" + unixTimestamp);
            return String.valueOf(unixTimestamp);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 传入时间戳 如 1405465664
     *
     * @param year_month_day
     * @return
     */
    public static String timeToStr(String year_month_day) {
        if (year_month_day == null || "".equals(year_month_day) || "null".equals(year_month_day)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        String format = sdf.format(new Date((Integer.valueOf(year_month_day)) * 1000L));
        return format;
    }

    /**
     * 获取当前日历
     * return 年月日星期
     */
    public static String getCalendar() {
        Calendar ca = Calendar.getInstance(Locale.CHINESE);
        ca.setTime(new Date());
        String Week = "";
        if (ca.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (ca.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }
        return ca.get(ca.YEAR) + "年" + (ca.get(ca.MONTH) + 1) + "月" + ca.get(ca.DAY_OF_MONTH) + "日 星期" + Week;
    }

    /**
     * 时间戳转为年月日时间
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String strTodate(String time) {
        try {
            Long timestamp = Long.valueOf(time) * 1000;
            Timestamp unixTime = new Timestamp(timestamp);
            SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");
            String d = format.format(unixTime);
            return d;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转为年月日时间
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String strTodateConnect(String time) {
        try {
            Long timestamp = Long.valueOf(time) * 1000;
            Timestamp unixTime = new Timestamp(timestamp);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String d = format.format(unixTime);
            return d;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转为月日具体的时间
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String strTodateAndTime(String time) {
        try {
            Long timestamp = Long.valueOf(time) * 1000;
            Timestamp unixTime = new Timestamp(timestamp);
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
            String d = format.format(unixTime);
            return d;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转为年月日具体的时间
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String strdateAndTime(String time) {
        try {
            Long timestamp = Long.valueOf(time) * 1000;
            Timestamp unixTime = new Timestamp(timestamp);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String d = format.format(unixTime);
            return d;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 时间格式控制:今天只显示时间 ,其他时候显示日期
     */
    public static String stampToHumanDate(long timestamp) {
        try {
            Timestamp unixTime = new Timestamp(timestamp * 1000);
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            SimpleDateFormat simpformat = new SimpleDateFormat("HH:mm"); // 转换为小时和时间
            SimpleDateFormat complexFormat = new SimpleDateFormat("MM-dd");
            /********* 当前时间与当前时间的参数获取 *****************/
            Date dNow = new Date(); // 当前时间
            Date dBefore = new Date();
            Calendar calendar = Calendar.getInstance(); // 得到日历
            calendar.setTime(dNow);// 把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
            dBefore = calendar.getTime(); // 得到前一天的时间
            String dbeforeStr = format.format(dBefore);//当前时间的前一天
            /***********************************/
            String dCurrent = format.format(unixTime);//传入的时间
            if (dbeforeStr.compareTo(dCurrent) >= 0) {//以前
                return complexFormat.format(unixTime);
            } else if (dbeforeStr.compareTo(dCurrent) == 0) {//昨天
                return "昨天" + simpformat.format(unixTime);
            } else if (dbeforeStr.compareTo(dCurrent) < 0) {//今天
                return simpformat.format(unixTime);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String stamp2humanDate(long timestamp) {
        try {
            Timestamp unixTime = new Timestamp(timestamp * 1000);
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            SimpleDateFormat simpformat = new SimpleDateFormat("HH:mm"); // 转换为小时和时间
            SimpleDateFormat complexFormat = new SimpleDateFormat("MM月dd日  HH:mm");
            /********* 当前时间与当前时间的参数获取 *****************/
            Date dNow = new Date(); // 当前时间
            Date dBefore = new Date();
            Calendar calendar = Calendar.getInstance(); // 得到日历
            calendar.setTime(dNow);// 把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
            dBefore = calendar.getTime(); // 得到前一天的时间
            String dbeforeStr = format.format(dBefore);
            /***********************************/
            String dCurrent = format.format(unixTime);
            if (dbeforeStr.compareTo(dCurrent) >= 0) {
                return complexFormat.format(unixTime);
            } else if (dbeforeStr.compareTo(dCurrent) == 0) {
                return "昨天" + simpformat.format(unixTime);
            } else if (dbeforeStr.compareTo(dCurrent) < 0) {
                return simpformat.format(unixTime);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把时间戳转化为date
     *
     * @param time
     * @return
     */
    public static Date stampToDate(String time) {
        if (time != null) {
            Long timestamp = Long.valueOf(time) * 1000;
            Timestamp unixTime = new Timestamp(timestamp);
            return unixTime;
        }
        return null;
    }

    /**
     * 把最原始的date转换为字符串时间戳
     *
     * @param date
     * @return
     */
    public static String DateToStamp(Date date) {
        if (date != null) {
            long unixTimestamp = date.getTime() / 1000;
            return String.valueOf(unixTimestamp);
        }
        return null;
    }

    /**
     * 比较两个时间的大小
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean compareDate(Date first, Date second) {
        if (first != null && second != null) {
            if (first.getTime() > second.getTime()) {
                return true;
            }
            return false;
        }
        return false;

    }
    /** 根据传入的数值转为周的形式 */
    public static String getWeekDay(int day){
        if(day == 1){
            return "周一";
        }else if(day == 2){
            return  "周二";
        }else if(day == 3){
            return  "周三";
        }else if(day == 4){
            return  "周四";
        }else if(day == 5){
            return  "周五";
        }else if(day == 6){
            return  "周六";
        }else if(day == 0){
            return  "周日";
        }
        return "";

    }

    /**
     * @param savedTime 保存的系统当前时间的毫秒数
     * @return true表示需要更新，否则不更新
     * 保存的时间：
     * 是否是今天：如果不是更新数据，
     * 如果是今天，如果是9点以后则不需要更新，如果不是，则判断当前时间是否是9点以后：如果是则更新，否则不更新
     */

    public static boolean needUpdateDB(long savedTime) {
        long nowDate = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowDate);
        int now_day = calendar.get(Calendar.DAY_OF_YEAR);
        int now_hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.setTimeInMillis(savedTime);
        int saved_day = calendar.get(Calendar.DAY_OF_YEAR);
        int saved_hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (now_day != saved_day) {//同一天
            return true;
        }
        return false;
    }

    /** 获得当前的年月日 */
    public static String longTimeToStr(long millions) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(millions * 1000);

        return sdf.format(date);
    }

    /** 获得当前的年月日 */
    public static String nowDateToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        return sdf.format(date);
    }

    /** 获得当前年，月，日，时，分 */
    public static String timeToDate(long millions) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date(millions * 1000);

        return sdf.format(date);
    }
    /** 获得当前年，月，日，时，分 */
    public static String timeToFullStr(long millions) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        Date date = new Date(millions * 1000);

        return sdf.format(date);
    }
    /** 获得当前时，分 */
    public static String timeToHM(long millions) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(millions * 1000);

        return sdf.format(date);
    }
    /** 获得当前年，月，日，时，分，秒*/
    public static String nowTimeToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return sdf.format(date);
    }


    /** 将毫秒转换为 2017-08-22 13:57:45 */
    public static String ms2Date(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    /** 和当前时间比较大小 */
    public static boolean comparDate(String time){

        try {
            /**如果想比较时间则写成"yyyy-MM-dd hh:mm:ss"就可以了*/
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            //将字符串形式的时间转化为Date类型的时间
            Date a = null;
            a = sdf.parse(time);
            Date b = new Date(System.currentTimeMillis());

            //Date类的一个方法，如果a早于b返回true，否则返回false
            if(a.before(b))
                return true;
            else
                return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

}
