package com.bend.his.common;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtil extends DateUtils {

    private static String[] parsePatterns = {

            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd",
            "yyyy-MM",

            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm",
            "yyyy/MM/dd",
            "yyyy/MM",

            "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm",
            "yyyy.MM.dd",
            "yyyy.MM",

            "yyyyMMdd HH:mm:ss",
            "yyyyMMddHHmmssSSS",
            "yyyyMMddHHmmss"
    };


    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (date == null) return "";
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期时间字符串，转换格式（yyyyMMddHHmmss）
     */
    public static String formatTime(Date date) {
        return formatDate(date, "yyyyMMddHHmmss");
    }

    /**
     * 字符时间戳-毫秒级
     *
     * @param date
     * @return
     */
    public static String formatTimestamp(Date date) {
        return formatDate(date, "yyyyMMddHHmmssSSS");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * date格式日期，只获取y-m-d
     *
     * @param nowDate
     * @return
     */
    public static Date getYmdDate(Date nowDate) {
        String strDate = formatDate(nowDate, "yyyy-MM-dd");
        return parseDate(strDate);
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }


    /**
     * 根据今天获取昨天
     *
     * @param today
     * @return
     */
    public static Date getYesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days      days 加减的天数
     * @return
     */
    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate
     * @param days
     * @return 字符串
     */
    public static String formatDateAddDays(Date startDate, int days) {
        return formatDateTime(dateAddDays(startDate, days));
    }

    /**
     * 根据今天获取本周-日期列表
     *
     * @param today
     * @return
     */
    public static List<String> getThisWeek(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            timeList.add(DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (cal.getTime().getTime() > today.getTime()) {
                break;
            }
        }
        return timeList;
    }

    /**
     * 根据今天获取上周--日期列表
     *
     * @param today
     * @return
     */
    public static List<String> getLastWeek(Date today) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(today);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);

        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            timeList.add(DateUtil.formatDate(calendar1.getTime(), "yyyy-MM-dd"));
            calendar1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return timeList;
    }

    /**
     * 根据今天获取最近n天 --日期列表
     *
     * @param today
     * @return
     */
    public static List<String> getLastMonthDate(Date today, int count) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(today);
        // 将时分秒,毫秒域清零
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            timeList.add(DateUtil.formatDate(calendar1.getTime(), "yyyy-MM-dd"));
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }

        return timeList;
    }


    /**
     * 日期增加天数
     *
     * @param date
     * @param iCount
     * @return
     */
    public static Date getAddDate(Date date, int iCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, iCount);
        return cal.getTime();
    }


    /**
     * 比较两个日期
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static int compareDate(String dateStr1, String dateStr2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(dateStr1);
            Date dt2 = df.parse(dateStr2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        Date ymdDate1 = getYmdDate(date1);
        Date ymdDate2 = getYmdDate(date2);
        try {
            if (ymdDate1.getTime() > ymdDate2.getTime()) {
                return 1;
            } else if (ymdDate1.getTime() < ymdDate2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 今天是否是周日
     *
     * @param date 当前日期
     * @return yes or no
     */
    public static boolean isSunday(Date date) {
        //date = parseDate("2019-06-16");
        String weekSunday1 = formatDate(date);
        String weekSunday2 = getWeekSunday(date);
        if (weekSunday1.equals(weekSunday2)) {
            return true;
        }
        return false;
    }

    /**
     * 查询当周周日
     *
     * @param date
     * @return 当周周日 格式yyyy-MM-dd
     */
    public static String getWeekSunday(Date date) {
        Date date1 = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.WEEK_OF_YEAR, 1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, 1);
        return formatDate(cal.getTime());
    }

    /**
     * 判断是不是周六日
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isWeek(Date date) {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            flag = true;
        }
        return flag;
    }


    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    @Deprecated
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取某天距某天之间间隔 --日期列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Deprecated
    public static List<String> getBetweenDate(Date startDate, Date endDate) {
        int max = 90;// 最大90天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        // 将时分秒,毫秒域清零
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endDate);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);

        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            timeList.add(DateUtil.formatDate(calendar1.getTime(), "yyyy-MM-dd"));
            calendar1.add(Calendar.DAY_OF_MONTH, 1);
            if (calendar1.getTime().getTime() > calendar2.getTime().getTime()) {
                break;
            }
        }
        return timeList;
    }


    /*************************************2019-07-09*********************************************/
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);

        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取当前时间:中文格式
     */
    public static String getCurrentTimeCN() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_LONG_CN);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**********************************************************************************/


}