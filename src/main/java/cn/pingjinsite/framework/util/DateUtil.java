package cn.pingjinsite.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 日期工具类
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月23日 下午8:47:10
 */
public class DateUtil {

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    // 日期格式化
    private static DateFormat dateFormat = null;

    // 时间格式化
    private static DateFormat dateTimeFormat = null;

    private static DateFormat timeFormat = null;

    static {
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
        timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
    }

    /**
     * @Title: getDateFormat
     * @Description: 日期格式化yyyy-MM-dd
     * @param date
     * @return String
     */
    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    /**
     * @Title: getDateFormat
     * @Description: 日期格式化yyyy-MM-dd
     * @param date
     * @return Date
     */
    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: getDateTimeFormat
     * @Description: 日期格式化yyyy-MM-dd HH:mm:ss
     * @param date
     * @return String
     */
    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * @Title: getDateTimeFormat
     * @Description: 日期格式化yyyy-MM-dd HH:mm:ss
     * @param date
     * @return Date
     */
    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: getTimeFormat
     * @Description: 时间格式化 HH:mm:ss
     * @param date
     * @return String
     */
    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    /**
     * @Title: getDistanceSecond
     * @Description: 返回当前时间与给定时间 相差多少秒
     * @param date 给定的时间
     * @return long 秒为单位
     */
    public static long getDistanceSecond(String date) {
        Date now = new Date();
        Date old = getDateTimeFormat(date);
        long distance = now.getTime() - old.getTime(); // 相差多少毫秒
        return distance / 1000;
    }

    /**
     * @Title: getCustomDateTime
     * @Description: 返回自定义日期 根据与当前日期做比较 比如：前几日 前几小时 前好多分钟等
     * @param date 给定日期
     * @return String 结果
     */
    public static String getCustomDateTime(String date) {
        long distance = getDistanceSecond(date); // 当前时间与给定时间相差多少秒数
        if (5 * 60 > distance) {
            // 5分钟以下
            return "刚刚";
        } else if (1 * 60 * 60 > distance && distance >= 5 * 60) {
            // 1个小时~5分钟
            return distance / 60 + "分钟前";
        } else if (24 * 60 * 60 > distance && distance >= 60 * 60) {
            // 1天 ~ 1个小时
            return distance / 3600 + "小时前";
        } else if (30 * 24 * 60 * 60 > distance && distance >= 24 * 60 * 60) {
            // 30天~1天
            return distance / (24 * 3600) + "天前";
        } else {
            return DateUtil.getDateFormat(DateUtil.getDateFormat(date));
        }
    }
}
