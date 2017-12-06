package com.catvgd.springbootdemo.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 日期处理共通函数
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class DateUtil implements ConstantDate {

    private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    /** yyyy */
    public static final SimpleDateFormat DATE_FORMAT_YYYY = new SimpleDateFormat("yyyy");
    /** yyyyMM */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM = new SimpleDateFormat("yyyyMM");
    /** yyyyMMdd */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
    /** yyyyMMddHH */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH = new SimpleDateFormat("yyyyMMddHH");
    /** yyyyMMddHHmm */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyyMMddHHmm");
    /** yyyyMMddHHmmss */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyyMMddHHmmss");
    /** yyyyMMddHHmmssSSS */
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /** yyyy-MM */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM = new SimpleDateFormat("yyyy-MM");
    /** yyyy-MM-dd */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    /** yyyy-MM-dd HH */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM_DD_HH = new SimpleDateFormat("yyyy-MM-dd HH");
    /** yyyy-MM-dd HH:mm */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /** yyyy-MM-dd HH:mm:ss */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** yyyy-MM-dd HH:mm:ss,SSS */
    public static final SimpleDateFormat DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    /** yyyy/MM */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM = new SimpleDateFormat("yyyy/MM");
    /** yyyy/MM/dd */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM_DD = new SimpleDateFormat("yyyy/MM/dd");
    /** yyyy/MM/dd HH */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM_DD_HH = new SimpleDateFormat("yyyy/MM/dd HH");
    /** yyyy/MM/dd HH:mm */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    /** yyyy/MM/dd HH:mm:ss */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /** yyyy/MM/dd HH:mm:ss,SSS */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS");

    /** yyyy年MM月 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM = new SimpleDateFormat("yyyy年MM月");
    /** yyyy年MM月dd日 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM_DD = new SimpleDateFormat("yyyy年MM月dd日");
    /** yyyy年MM月dd日 HH时 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM_DD_HH = new SimpleDateFormat("yyyy年MM月dd日 HH时");
    /** yyyy年MM月dd日 HH时mm分 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
    /** yyyy年MM月dd日 HH时mm分ss秒 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    /** yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 */
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");

    /** yyyy-MM-dd_HH-mm-ss */
    public static final SimpleDateFormat DATE_FORMAT_FILE_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    /** 标准日期格式 */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyy");
    /** 标准时间格式 */
    public static final SimpleDateFormat DATE_FORMAT_SLASH_MM_DD_YYYY_HH_MM = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    /**
     * 格式化日期
     * 
     * @param date 要转换的日期
     * @param inPattern 转换前的格式
     * @param outPattern 转换后的格式
     * @return
     */
    public static synchronized String dateFormat(String date, SimpleDateFormat inPattern, SimpleDateFormat outPattern) {
        if (StringUtil.isEmpty(date) || inPattern == null || outPattern == null) {
            return "";
        } else {
            return getDateFormat(DateUtil.parseDateFormat(date, inPattern.toPattern()), outPattern.toPattern());
        }
    }

    /**
     * 取得Calendar
     * 
     * @return Calendar
     */
    public static synchronized Calendar getCalendar() {
        return GregorianCalendar.getInstance();
    }

    // ===================================================================== //
    // 无斜线、无破折号
    // ===================================================================== //

    /**
     * pattern = "yyyy"
     * 
     * @return String
     */
    public static synchronized String getDateYearFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateYearFormat(cal);
    }

    /**
     * pattern = "yyyy"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateYearFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateYearFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarYearFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateYearFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMM"
     * 
     * @return String
     */
    public static synchronized String getDateMonthFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMonthFormat(cal);
    }

    /**
     * pattern = "yyyyMM"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMonthFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMM"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMonthFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMM"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMonthFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMM"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMonthFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMMdd"
     * 
     * @return String
     */
    public static synchronized String getDateDayFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateDayFormat(cal);
    }

    /**
     * pattern = "yyyyMMdd"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateDayFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM_DD.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMMdd"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateDayFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM_DD.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMMdd"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarDayFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMMdd"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateDayFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMMddHH"
     * 
     * @return String
     */
    public static synchronized String getDateHourFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateHourFormat(cal);
    }

    /**
     * pattern = "yyyyMMddHH"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateHourFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMMddHH"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateHourFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMMddHH"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarHourFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMMddHH"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateHourFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMMddHHmm"
     * 
     * @return String
     */
    public static synchronized String getDateMinuteFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMinuteFormat(cal);
    }

    /**
     * pattern = "yyyyMMddHHmm"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMinuteFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmm"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMinuteFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmm"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMinuteFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmm"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMinuteFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMMddHHmmss"
     * 
     * @return String
     */
    public static synchronized String getDateSecondFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateSecondFormat(cal);
    }

    /**
     * pattern = "yyyyMMddHHmmss"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateSecondFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmss"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateSecondFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmss"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarSecondFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmss"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateSecondFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyyMMddHHmmssSSS"
     * 
     * @return String
     */
    public static synchronized String getDateMilliFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMilliFormat(cal);
    }

    /**
     * pattern = "yyyyMMddHHmmssSSS"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMilliFormat(Calendar cal) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmssSSS"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMilliFormat(Date date) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmssSSS"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMilliFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyyMMddHHmmssSSS"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMilliFormat(String strDate) {
        String pattern = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //
    // 破折号
    // ===================================================================== //

    /**
     * pattern = "yyyy-MM"
     * 
     * @return String
     */
    public static synchronized String getDateMonthDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMonthDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMonthDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMonthDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMonthDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMonthDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd"
     * 
     * @return String
     */
    public static synchronized String getDateDayDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateDayDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateDayDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateDayDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarDayDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateDayDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd HH"
     * 
     * @return String
     */
    public static synchronized String getDateHourDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateHourDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd HH"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateHourDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateHourDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarHourDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateHourDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd HH:mm"
     * 
     * @return String
     */
    public static synchronized String getDateMinuteDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMinuteDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMinuteDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMinuteDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMinuteDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMinuteDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss"
     * 
     * @return String
     */
    public static synchronized String getDateSecondDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateSecondDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateSecondDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateSecondDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarSecondDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateSecondDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss,SSS"
     * 
     * @return String
     */
    public static synchronized String getDateMilliDashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMilliDashFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss,SSS"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMilliDashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss,SSS"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMilliDashFormat(Date date) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss,SSS"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMilliDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd HH:mm:ss,SSS"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMilliDashFormat(String strDate) {
        String pattern = DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //
    // 斜线
    // ===================================================================== //

    /**
     * pattern = "yyyy/MM"
     * 
     * @return String
     */
    public static synchronized String getDateMonthSlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMonthSlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMonthSlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMonthSlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMonthSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMonthSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy/MM/dd"
     * 
     * @return String
     */
    public static synchronized String getDateDaySlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateDaySlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM/dd"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateDaySlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateDaySlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarDaySlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateDaySlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy/MM/dd HH"
     * 
     * @return String
     */
    public static synchronized String getDateHourSlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateHourSlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM/dd HH"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateHourSlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateHourSlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarHourSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateHourSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy/MM/dd HH:mm"
     * 
     * @return String
     */
    public static synchronized String getDateMinuteSlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMinuteSlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMinuteSlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMinuteSlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMinuteSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMinuteSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss"
     * 
     * @return String
     */
    public static synchronized String getDateSecondSlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateSecondSlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateSecondSlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateSecondSlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarSecondSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateSecondSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss,SSS"
     * 
     * @return String
     */
    public static synchronized String getDateMilliSlashFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMilliSlashFormat(cal);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss,SSS"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMilliSlashFormat(Calendar cal) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss,SSS"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMilliSlashFormat(Date date) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss,SSS"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMilliSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy/MM/dd HH:mm:ss,SSS"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMilliSlashFormat(String strDate) {
        String pattern = DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //
    // 带有汉字格式的日期
    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月"
     * 
     * @return String
     */
    public static synchronized String getDateMonthChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMonthChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMonthChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMonthChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMonthChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMonthChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月dd日"
     * 
     * @return String
     */
    public static synchronized String getDateDayChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateDayChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月dd日"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateDayChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateDayChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarDayChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateDayChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月dd日 HH时"
     * 
     * @return String
     */
    public static synchronized String getDateHourChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateHourChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateHourChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateHourChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日HH时"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarHourChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateHourChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分"
     * 
     * @return String
     */
    public static synchronized String getDateMinuteChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMinuteChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMinuteChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMinuteChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日HH时mm分"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMinuteChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMinuteChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒"
     * 
     * @return String
     */
    public static synchronized String getDateSecondChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateSecondChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateSecondChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateSecondChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日HH时mm分ss秒"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarSecondChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateSecondChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"
     * 
     * @return String
     */
    public static synchronized String getDateMilliChineseFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateMilliChineseFormat(cal);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateMilliChineseFormat(Calendar cal) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateMilliChineseFormat(Date date) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarMilliChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateMilliChineseFormat(String strDate) {
        String pattern = DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //
    // 文件格式的日期
    // ===================================================================== //

    /**
     * pattern = "yyyy-MM-dd_HH-mm-ss"
     * 
     * @return String
     */
    public static synchronized String getDateFileFormat() {
        Calendar cal = Calendar.getInstance();
        return getDateFileFormat(cal);
    }

    /**
     * pattern = "yyyy-MM-dd_HH-mm-ss"
     * 
     * @param cal
     * @return String
     */
    public static synchronized String getDateFileFormat(Calendar cal) {
        String pattern = DATE_FORMAT_FILE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(cal, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd_HH-mm-ss"
     * 
     * @param date
     * @return String
     */
    public static synchronized String getDateFileFormat(Date date) {
        String pattern = DATE_FORMAT_FILE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return getDateFormat(date, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd_HH-mm-ss"
     * 
     * @param strDate
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarFileFormat(String strDate) {
        String pattern = DATE_FORMAT_FILE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     * pattern = "yyyy-MM-dd_HH-mm-ss"
     * 
     * @param strDate
     * @return Date
     */
    public static synchronized Date parseDateFileFormat(String strDate) {
        String pattern = DATE_FORMAT_FILE_YYYY_MM_DD_HH_MM_SS.toPattern();
        return parseDateFormat(strDate, pattern);
    }

    // ===================================================================== //
    // 日期转换共通
    // ===================================================================== //

    /**
     * 返回指定格式的日期字符串
     * 
     * @param cal
     * @param pattern
     * @return String
     */
    public static synchronized String getDateFormat(Calendar cal, String pattern) {
        return getDateFormat(cal.getTime(), pattern);
    }

    /**
     * 返回指定格式的日期字符串
     * 
     * @param date
     * @param pattern
     * @return String
     */
    public static synchronized String getDateFormat(Date date, String pattern) {
        synchronized (sdf) {
            String str = null;
            sdf.applyPattern(pattern);
            str = sdf.format(date);
            return str;
        }
    }

    /**
     * 把日期字符串根据指定格式转换成日期
     * 
     * @param strDate
     * @param pattern
     * @return Calendar
     */
    public static synchronized Calendar parseCalendarFormat(String strDate, String pattern) {
        synchronized (sdf) {
            Calendar cal = null;
            sdf.applyPattern(pattern);
            try {
                sdf.parse(strDate);
                cal = sdf.getCalendar();
            } catch (Exception e) {
            }
            return cal;
        }
    }

    /**
     * 把日期字符串根据指定格式转换成日期
     * 
     * @param strDate
     * @param pattern
     * @return Date
     */
    public static synchronized Date parseDateFormat(String strDate, String pattern) {
        synchronized (sdf) {
            Date date = null;
            sdf.applyPattern(pattern);
            try {
                date = sdf.parse(strDate);
            } catch (Exception e) {
            }
            return date;
        }
    }

    // ===================================================================== //
    // 日期的计算
    // ===================================================================== //

    /**
     * 取得当前年指定月的最后一天
     * 
     * @param month
     * @return
     */
    public static synchronized int getLastDayOfMonth(int month) {
        if (month < 1 || month > 12) {
            return -1;
        }
        int retn = 0;
        if (month == 2) {
            if (isLeapYear()) {
                retn = 29;
            } else {
                retn = dayArray[month - 1];
            }
        } else {
            retn = dayArray[month - 1];
        }
        return retn;
    }

    /**
     * 取得指定年指定月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static synchronized int getLastDayOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            return -1;
        }
        int retn = 0;
        if (month == 2) {
            if (isLeapYear(year)) {
                retn = 29;
            } else {
                retn = dayArray[month - 1];
            }
        } else {
            retn = dayArray[month - 1];
        }
        return retn;
    }

    /**
     * 判断当前日期的年份是否是闰年
     * 
     * @return
     */
    public static synchronized boolean isLeapYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
     * 
     * @param year
     * @return
     */
    public static synchronized boolean isLeapYear(int year) {
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 判断指定日期的年份是否是闰年
     * 
     * @param date 指定日期。
     * @return 是否闰年
     */
    public static synchronized boolean isLeapYear(Date date) {
        // int year = date.getYear();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 判断指定日期的年份是否是闰年
     * 
     * @param gc
     * @return
     */
    public static synchronized boolean isLeapYear(Calendar gc) {
        int year = gc.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 得到指定日期的前一个工作日
     * 
     * @param date 指定日期。
     * @return 指定日期的前一个工作日
     */
    public static synchronized Date getPreviousWeekDay(Date date) {
        // 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getPreviousWeekDay(gc);
    }

    /**
     * 得到指定日期的前一个工作日
     * 
     * @param gc
     * @return
     */
    public static synchronized Date getPreviousWeekDay(Calendar gc) {
        // 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, -3);
            break;
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, -2);
            break;
        default:
            gc.add(Calendar.DATE, -1);
            break;
        }
        return gc.getTime();
    }

    /**
     * 得到指定日期的后一个工作日
     * 
     * @param date 指定日期。
     * @return 指定日期的后一个工作日
     */
    public static synchronized Date getNextWeekDay(Date date) {
        // 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 2);
            break;
        default:
            gc.add(Calendar.DATE, 1);
            break;
        }
        return gc.getTime();
    }

    /**
     * 得到指定日期的后一个工作日
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getNextWeekDay(Calendar gc) {
        // 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 2);
            break;
        default:
            gc.add(Calendar.DATE, 1);
            break;
        }
        return gc;
    }

    /**
     * 取得指定日期的下一个月的最后一天
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个月的最后一天
     */
    public static synchronized Date getLastDayOfNextMonth(Date date) {
        // 1.调用getNextMonth设置当前时间 2.以1为基础，调用getLastDayOfMonth
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getLastDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个星期的最后一天(星期天作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的最后一天
     */
    public static synchronized Date getLastDayOfNextWeek(Date date) {
        // 1.调用getNextWeek设置当前时间 2.以1为基础，调用getLastDayOfWeek
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getLastDayOfWeek(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个星期的最后一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的最后一天
     */
    public static synchronized Date getLastDayOfNextWeek2(Date date) {
        // 1.调用getNextWeek设置当前时间 2.以1为基础，调用getLastDayOfWeek
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getLastDayOfWeek2(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个月的第一天
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个月的第一天
     */
    public static synchronized Date getFirstDayOfNextMonth(Date date) {
        // 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个月的第一天
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getFirstDayOfNextMonth(Calendar gc) {
        // 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
        return gc;
    }

    /**
     * 取得指定日期的下一个星期的第一天(星期天作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的第一天
     */
    public static synchronized Date getFirstDayOfNextWeek(Date date) {
        // 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个星期的第一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的第一天
     */
    public static synchronized Date getFirstDayOfNextWeek2(Date date) {
        // 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek2(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个星期的第一天(星期天作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的第一天
     */
    public static synchronized Calendar getFirstDayOfNextWeek(Calendar gc) {
        // 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
        return gc;
    }

    /**
     * 取得指定日期的下一个星期的第一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期的第一天
     */
    public static synchronized Calendar getFirstDayOfNextWeek2(Calendar gc) {
        // 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek2(gc.getTime()));
        return gc;
    }

    /**
     * 取得指定日期的下一年
     * 
     * @param date 指定日期。
     * @return 指定日期的下一年
     */
    public static synchronized Date getNextYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.YEAR, 1);
        return gc.getTime();
    }

    /**
     * 取得下一年
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getNextYear(Calendar gc) {
        gc.add(Calendar.YEAR, 1);
        return gc;
    }

    /**
     * 取得指定日期的前一年
     * 
     * @param date 指定日期。
     * @return 指定日期的前一年
     */
    public static synchronized Date getPreviousYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.YEAR, -1);
        return gc.getTime();
    }

    /**
     * 取得前一年
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getPreviousYear(Calendar gc) {
        gc.add(Calendar.YEAR, -1);
        return gc;
    }

    /**
     * 指定日期加N年
     * 
     * @param date 指定日期。
     * @param n n是数字
     * @return 指定日期加N年
     */
    public static synchronized Date getAddNYear(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.YEAR, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N年
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNYear(Calendar gc, int n) {
        gc.add(Calendar.YEAR, n);
        return gc;
    }

    /**
     * 取得指定日期的下一个月
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个月
     */
    public static synchronized Date getNextMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, 1);
        return gc.getTime();
    }

    /**
     * 取得下一个月
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getNextMonth(Calendar gc) {
        gc.add(Calendar.MONTH, 1);
        return gc;
    }

    /**
     * 取得指定日期的前一个月
     * 
     * @param date 指定日期。
     * @return 指定日期的前一个月
     */
    public static synchronized Date getPreviousMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -1);
        return gc.getTime();
    }

    /**
     * 取得前一个月
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getPreviousMonth(Calendar gc) {
        gc.add(Calendar.MONTH, -1);
        return gc;
    }

    /**
     * 指定日期加N月
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N月
     */
    public static synchronized Date getAddNMonth(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N月
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNMonth(Calendar gc, int n) {
        gc.add(Calendar.MONTH, n);
        return gc;
    }

    /**
     * 取得指定日期的下一天
     * 
     * @param date 指定日期。
     * @return 指定日期的下一天
     */
    public static synchronized Date getNextDay(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 1);
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一天
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getNextDay(Calendar gc) {
        gc.add(Calendar.DATE, 1);
        return gc;
    }

    /**
     * 取得指定日期的前一天
     * 
     * @param date 指定日期。
     * @return 指定日期的前一天
     */
    public static synchronized Date getPreviousDay(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, -1);
        return gc.getTime();
    }

    /**
     * 取得指定日期的前一天
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getPreviousDay(Calendar gc) {
        gc.add(Calendar.DATE, -1);
        return gc;
    }

    /**
     * 指定日期加N天
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N天
     */
    public static synchronized Date getAddNDay(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N天
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNDay(Calendar gc, int n) {
        gc.add(Calendar.DATE, n);
        return gc;
    }

    /**
     * 取得指定日期的下一个星期
     * 
     * @param date 指定日期。
     * @return 指定日期的下一个星期
     */
    public static synchronized Date getNextWeek(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 7);
        return gc.getTime();
    }

    /**
     * 取得指定日期的下一个星期
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getNextWeek(Calendar gc) {
        gc.add(Calendar.DATE, 7);
        return gc;
    }

    /**
     * 指定日期加N个星期
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N个星期
     */
    public static synchronized Date getAddNWeek(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 7 * n);
        return gc.getTime();
    }

    /**
     * 指定日期加N个星期
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNWeek(Calendar gc, int n) {
        gc.add(Calendar.DATE, 7 * n);
        return gc;
    }

    /**
     * 指定日期加N个小时
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N个小时
     */
    public static synchronized Date getAddNHour(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.HOUR_OF_DAY, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N个小时
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNHour(Calendar gc, int n) {
        gc.add(Calendar.HOUR_OF_DAY, n);
        return gc;
    }

    /**
     * 指定日期加N分钟
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N分钟
     */
    public static synchronized Date getAddNMinute(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MINUTE, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N分钟
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNMinute(Calendar gc, int n) {
        gc.add(Calendar.MINUTE, n);
        return gc;
    }

    /**
     * 指定日期加N秒
     * 
     * @param date 指定日期。
     * @param n
     * @return 指定日期加N秒
     */
    public static synchronized Date getAddNSecond(Date date, int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.SECOND, n);
        return gc.getTime();
    }

    /**
     * 指定日期加N秒
     * 
     * @param gc
     * @param n
     * @return
     */
    public static synchronized Calendar getAddNSecond(Calendar gc, int n) {
        gc.add(Calendar.SECOND, n);
        return gc;
    }

    // ===================================================================== //
    // 判定日期是星期几
    // ===================================================================== //

    /**
     * 取得当前日期是星期几
     * 
     * @return
     */
    public static synchronized int getWeekOfDay() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getWeekOfDay(gc);
    }

    /**
     * 取得指定日期是星期几
     * 
     * @param date
     * @return
     */
    public static synchronized int getWeekOfDay(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getWeekOfDay(gc);
    }

    /**
     * 取得指定日期是星期几
     * 
     * @param gc
     * @return
     */
    public static synchronized int getWeekOfDay(Calendar gc) {
        int week = 0;
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            week = 7;
            break;
        case (Calendar.MONDAY):
            week = 1;
            break;
        case (Calendar.TUESDAY):
            week = 2;
            break;
        case (Calendar.WEDNESDAY):
            week = 3;
            break;
        case (Calendar.THURSDAY):
            week = 4;
            break;
        case (Calendar.FRIDAY):
            week = 5;
            break;
        case (Calendar.SATURDAY):
            week = 6;
            break;
        }
        return week;
    }

    // ===================================================================== //
    // 判定日期是所在周中第几天
    // ===================================================================== //

    /**
     * 取得当前日期是所在周中第几天(星期日作为第一天)
     * 
     * @return
     */
    public static synchronized int getDayOfWeek() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getDayOfWeek(gc);
    }

    /**
     * 取得当前日期是所在周中第几天(星期一作为第一天)
     * 
     * @return
     */
    public static synchronized int getDayOfWeek2() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getDayOfWeek2(gc);
    }

    /**
     * 取得指定日期是所在周中第几天(星期日作为第一天)
     * 
     * @param date
     * @return
     */
    public static synchronized int getDayOfWeek(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getDayOfWeek(gc);
    }

    /**
     * 取得指定日期是所在周中第几天(星期一作为第一天)
     * 
     * @param date
     * @return
     */
    public static synchronized int getDayOfWeek2(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getDayOfWeek2(gc);
    }

    /**
     * 取得指定日期是所在周中第几天(星期日作为第一天)
     * 
     * @param gc
     * @return
     */
    public static synchronized int getDayOfWeek(Calendar gc) {
        return gc.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 取得指定日期是所在周中第几天(星期一作为第一天)
     * 
     * @param gc
     * @return
     */
    public static synchronized int getDayOfWeek2(Calendar gc) {
        int dayOfWeek = 0;
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            dayOfWeek = 7;
            break;
        case (Calendar.MONDAY):
            dayOfWeek = 1;
            break;
        case (Calendar.TUESDAY):
            dayOfWeek = 2;
            break;
        case (Calendar.WEDNESDAY):
            dayOfWeek = 3;
            break;
        case (Calendar.THURSDAY):
            dayOfWeek = 4;
            break;
        case (Calendar.FRIDAY):
            dayOfWeek = 5;
            break;
        case (Calendar.SATURDAY):
            dayOfWeek = 6;
            break;
        }
        return dayOfWeek;
    }

    // ===================================================================== //
    // 判定日期是所在月中第几天
    // ===================================================================== //

    /**
     * 取得当前日期是所在月中第几天
     * 
     * @return
     */
    public static synchronized int getDayOfMonth() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getDayOfMonth(gc);
    }

    /**
     * 取得指定日期是所在月中第几天
     * 
     * @param date
     * @return
     */
    public static synchronized int getDayOfMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getDayOfMonth(gc);
    }

    /**
     * 取得指定日期是所在月中第几天
     * 
     * @param gc
     * @return
     */
    public static synchronized int getDayOfMonth(Calendar gc) {
        return gc.get(Calendar.DAY_OF_MONTH);
    }

    // ===================================================================== //
    // 判定日期是所在年中第几天
    // ===================================================================== //

    /**
     * 取得当前日期是所在年中第几天
     * 
     * @return
     */
    public static synchronized int getDayOfYear() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getDayOfYear(gc);
    }

    /**
     * 取得指定日期是所在年中第几天
     * 
     * @param date
     * @return
     */
    public static synchronized int getDayOfYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getDayOfYear(gc);
    }

    /**
     * 取得指定日期是所在年中第几天
     * 
     * @param gc
     * @return
     */
    public static synchronized int getDayOfYear(Calendar gc) {
        return gc.get(Calendar.DAY_OF_YEAR);
    }

    // ===================================================================== //
    // 判定日期是所在月中第几周
    // ===================================================================== //

    /**
     * 取得当前日期是所在月中第几周
     * 
     * @param date
     * @return
     */
    public static synchronized int getWeekOfMonth() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getWeekOfMonth(gc);
    }

    /**
     * 取得指定日期是所在月中第几周
     * 
     * @param date
     * @return
     */
    public static synchronized int getWeekOfMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getWeekOfMonth(gc);
    }

    /**
     * 取得指定日期是所在月中第几周
     * 
     * @param gc
     * @return
     */
    public static synchronized int getWeekOfMonth(Calendar gc) {
        return gc.get(Calendar.WEEK_OF_MONTH);
    }

    // ===================================================================== //
    // 判定日期是当年第几周
    // ===================================================================== //

    /**
     * 取得当前日期是当年第几周
     * 
     * @return
     */
    public static synchronized int getWeekOfYear() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        return getWeekOfYear(gc);
    }

    /**
     * 取得指定日期是当年第几周
     * 
     * @param date
     * @return
     */
    public static synchronized int getWeekOfYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return getWeekOfYear(gc);
    }

    /**
     * 取得指定日期是当年第几周
     * 
     * @param gc
     * @return
     */
    public static synchronized int getWeekOfYear(Calendar gc) {
        return gc.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得指定日期的所处星期的最后一天(星期日作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的最后一天
     */
    public static synchronized Date getLastDayOfWeek(Date date) {
        // 1.如果date是星期日，则加6天
        // 2.如果date是星期一，则加5天
        // 3.如果date是星期二，则加4天
        // 4.如果date是星期三，则加3天
        // 5.如果date是星期四，则加2天
        // 6.如果date是星期五，则加1天
        // 7.如果date是星期六，则加0天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 6);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 5);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, 4);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, 2);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 1);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 0);
            break;
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处星期的最后一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的最后一天
     */
    public static synchronized Date getLastDayOfWeek2(Date date) {
        // 1.如果date是星期日，则加0天
        // 2.如果date是星期一，则加6天
        // 3.如果date是星期二，则加5天
        // 4.如果date是星期三，则加4天
        // 5.如果date是星期四，则加3天
        // 6.如果date是星期五，则加2天
        // 7.如果date是星期六，则加1天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 6);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, 5);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, 4);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 2);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 1);
            break;
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处星期的最后一天(星期日作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的最后一天
     */
    public static synchronized Calendar getLastDayOfWeek(Calendar gc) {
        // 1.如果date是星期日，则加6天
        // 2.如果date是星期一，则加5天
        // 3.如果date是星期二，则加4天
        // 4.如果date是星期三，则加3天
        // 5.如果date是星期四，则加2天
        // 6.如果date是星期五，则加1天
        // 7.如果date是星期六，则加0天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 6);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 5);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, 4);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, 2);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 1);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 0);
            break;
        }
        return gc;
    }

    /**
     * 取得指定日期的所处星期的最后一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的最后一天
     */
    public static synchronized Calendar getLastDayOfWeek2(Calendar gc) {
        // 1.如果date是星期日，则加0天
        // 2.如果date是星期一，则加6天
        // 3.如果date是星期二，则加5天
        // 4.如果date是星期三，则加4天
        // 5.如果date是星期四，则加3天
        // 6.如果date是星期五，则加2天
        // 7.如果date是星期六，则加1天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 6);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, 5);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, 4);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, 3);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, 2);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, 1);
            break;
        }
        return gc;
    }

    /**
     * 取得指定日期的所处星期的第一天(星期日作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的第一天
     */
    public static synchronized Date getFirstDayOfWeek(Date date) {
        // 1.如果date是星期日，则减0天
        // 2.如果date是星期一，则减1天
        // 3.如果date是星期二，则减2天
        // 4.如果date是星期三，则减3天
        // 5.如果date是星期四，则减4天
        // 6.如果date是星期五，则减5天
        // 7.如果date是星期六，则减6天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, -1);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, -2);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, -3);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, -4);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, -5);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, -6);
            break;
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处星期的第一天(星期一作为第一天)
     * 
     * @param date 指定日期。
     * @return 指定日期的所处星期的第一天
     */
    public static synchronized Date getFirstDayOfWeek2(Date date) {
        // 1.如果date是星期日，则减6天
        // 2.如果date是星期一，则减0天
        // 3.如果date是星期二，则减1天
        // 4.如果date是星期三，则减2天
        // 5.如果date是星期四，则减3天
        // 6.如果date是星期五，则减4天
        // 7.如果date是星期六，则减5天
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, -6);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, -1);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, -2);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, -3);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, -4);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, -5);
            break;
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处星期的第一天(星期日作为第一天)
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getFirstDayOfWeek(Calendar gc) {
        // 1.如果date是星期日，则减0天
        // 2.如果date是星期一，则减1天
        // 3.如果date是星期二，则减2天
        // 4.如果date是星期三，则减3天
        // 5.如果date是星期四，则减4天
        // 6.如果date是星期五，则减5天
        // 7.如果date是星期六，则减6天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, -1);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, -2);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, -3);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, -4);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, -5);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, -6);
            break;
        }
        return gc;
    }

    /**
     * 取得指定日期的所处星期的第一天(星期一作为第一天)
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getFirstDayOfWeek2(Calendar gc) {
        // 1.如果date是星期日，则减6天
        // 2.如果date是星期一，则减0天
        // 3.如果date是星期二，则减1天
        // 4.如果date是星期三，则减2天
        // 5.如果date是星期四，则减3天
        // 6.如果date是星期五，则减4天
        // 7.如果date是星期六，则减5天
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            gc.add(Calendar.DATE, -6);
            break;
        case (Calendar.MONDAY):
            gc.add(Calendar.DATE, 0);
            break;
        case (Calendar.TUESDAY):
            gc.add(Calendar.DATE, -1);
            break;
        case (Calendar.WEDNESDAY):
            gc.add(Calendar.DATE, -2);
            break;
        case (Calendar.THURSDAY):
            gc.add(Calendar.DATE, -3);
            break;
        case (Calendar.FRIDAY):
            gc.add(Calendar.DATE, -4);
            break;
        case (Calendar.SATURDAY):
            gc.add(Calendar.DATE, -5);
            break;
        }
        return gc;
    }

    /**
     * 取得指定日期的所处月份的最后一天
     * 
     * @param date 指定日期。
     * @return 指定日期的所处月份的最后一天
     */
    public static synchronized Date getLastDayOfMonth(Date date) {
        // 1.如果date在1月，则为31日
        // 2.如果date在2月，则为28日
        // 3.如果date在3月，则为31日
        // 4.如果date在4月，则为30日
        // 5.如果date在5月，则为31日
        // 6.如果date在6月，则为30日
        // 7.如果date在7月，则为31日
        // 8.如果date在8月，则为31日
        // 9.如果date在9月，则为30日
        // 10.如果date在10月，则为31日
        // 11.如果date在11月，则为30日
        // 12.如果date在12月，则为31日
        // 1.如果date在闰年的2月，则为29日
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.MONTH)) {
        case 0:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 1:
            gc.set(Calendar.DAY_OF_MONTH, 28);
            break;
        case 2:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 3:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 4:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 5:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 6:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 7:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 8:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 9:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 10:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 11:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        }
        // 检查闰年
        if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
            gc.set(Calendar.DAY_OF_MONTH, 29);
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处月份的最后一天
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getLastDayOfMonth(Calendar gc) {
        // 1.如果date在1月，则为31日
        // 2.如果date在2月，则为28日
        // 3.如果date在3月，则为31日
        // 4.如果date在4月，则为30日
        // 5.如果date在5月，则为31日
        // 6.如果date在6月，则为30日
        // 7.如果date在7月，则为31日
        // 8.如果date在8月，则为31日
        // 9.如果date在9月，则为30日
        // 10.如果date在10月，则为31日
        // 11.如果date在11月，则为30日
        // 12.如果date在12月，则为31日
        // 1.如果date在闰年的2月，则为29日
        switch (gc.get(Calendar.MONTH)) {
        case 0:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 1:
            gc.set(Calendar.DAY_OF_MONTH, 28);
            break;
        case 2:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 3:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 4:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 5:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 6:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 7:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 8:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 9:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 10:
            gc.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 11:
            gc.set(Calendar.DAY_OF_MONTH, 31);
            break;
        }
        // 检查闰年
        if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
            gc.set(Calendar.DAY_OF_MONTH, 29);
        }
        return gc;
    }

    /**
     * 取得指定日期的所处月份的第一天
     * 
     * @param date 指定日期。
     * @return 指定日期的所处月份的第一天
     */
    public static synchronized Date getFirstDayOfMonth(Date date) {
        // 1.设置为1号
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处月份的第一天
     * 
     * @param gc
     * @return
     */
    public static synchronized Calendar getFirstDayOfMonth(Calendar gc) {
        // 1.设置为1号
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc;
    }

    /**
     * 取得指定日期所处月份的指定星期的所有日期
     * 
     * @param date 指定日期
     * @param week 指定星期(1,2,3,4,5,6,7)
     * @return
     */
    public static synchronized List<Date> getAllDayOfMonthByWeek(Date date, int week) {
        List<Date> list = new ArrayList<Date>();
        // 取得当前月的第一天
        GregorianCalendar firstGc = (GregorianCalendar) Calendar.getInstance();
        firstGc.setTime(getFirstDayOfMonth(date));
        // 取得当前月的最后一天
        GregorianCalendar lastGc = (GregorianCalendar) Calendar.getInstance();
        lastGc.setTime(getLastDayOfMonth(date));
        // 取得当前月的所有天数
        int dayOfMonth = lastGc.get(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= dayOfMonth; i++) {
            if (firstGc.get(Calendar.DAY_OF_WEEK) == ((week % 7) + 1)) {
                list.add(firstGc.getTime());
            }
            firstGc.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 取得指定日期所处月份的指定星期的所有日期
     * 
     * @param date 指定日期
     * @param week 指定星期(1,2,3,4,5,6,7)
     * @param pattern 返回格式
     * @return
     */
    public static synchronized List<String> getAllDayOfMonthByWeek(Date date, int week, SimpleDateFormat pattern) {
        List<String> list = new ArrayList<String>();
        // 取得当前月的第一天
        GregorianCalendar firstGc = (GregorianCalendar) Calendar.getInstance();
        firstGc.setTime(getFirstDayOfMonth(date));
        // 取得当前月的最后一天
        GregorianCalendar lastGc = (GregorianCalendar) Calendar.getInstance();
        lastGc.setTime(getLastDayOfMonth(date));
        // 取得当前月的所有天数
        int dayOfMonth = lastGc.get(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= dayOfMonth; i++) {
            if (firstGc.get(Calendar.DAY_OF_WEEK) == ((week % 7) + 1)) {
                list.add(getDateFormat(firstGc.getTime(), pattern.toPattern()));
            }
            firstGc.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 取得当前月份中大于当前日期的指定星期的所有日期
     * 
     * @param week 指定星期(1,2,3,4,5,6,7)
     * @return
     */
    public static synchronized List<Date> getAfterTodayOfMonthByWeek(int week) {
        List<Date> list = new ArrayList<Date>();
        // 取得当前日期
        GregorianCalendar firstGc = (GregorianCalendar) Calendar.getInstance();
        firstGc.setTime(new Date());
        // 取得当前月的最后一天
        GregorianCalendar lastGc = (GregorianCalendar) Calendar.getInstance();
        lastGc.setTime(getLastDayOfMonth(new Date()));
        // 当前日期
        int today = firstGc.get(Calendar.DATE);
        // 取得当前月的所有天数
        int dayOfMonth = lastGc.get(Calendar.DAY_OF_MONTH);
        for (int i = today; i <= dayOfMonth; i++) {
            if (firstGc.get(Calendar.DAY_OF_WEEK) == ((week % 7) + 1)) {
                list.add(firstGc.getTime());
            }
            firstGc.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 取得当前月份中大于当前日期的指定星期的所有日期
     * 
     * @param week 指定星期(1,2,3,4,5,6,7)
     * @param pattern 返回格式
     * @return
     */
    public static synchronized List<String> getAfterTodayOfMonthByWeek(int week, SimpleDateFormat pattern) {
        List<String> list = new ArrayList<String>();
        // 取得当前日期
        GregorianCalendar firstGc = (GregorianCalendar) Calendar.getInstance();
        firstGc.setTime(new Date());
        // 取得当前月的最后一天
        GregorianCalendar lastGc = (GregorianCalendar) Calendar.getInstance();
        lastGc.setTime(getLastDayOfMonth(new Date()));
        // 当前日期
        int today = firstGc.get(Calendar.DATE);
        // 取得当前月的所有天数
        int dayOfMonth = lastGc.get(Calendar.DAY_OF_MONTH);
        for (int i = today; i <= dayOfMonth; i++) {
            if (firstGc.get(Calendar.DAY_OF_WEEK) == ((week % 7) + 1)) {
                list.add(getDateFormat(firstGc.getTime(), pattern.toPattern()));
            }
            firstGc.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 取得当前月份中大于当前日期的指定星期的所有日期
     * 
     * @param week 指定星期(1,2,3,4,5,6,7)
     * @param pattern 返回格式
     * @return
     */
    public static synchronized List<String> getAfterTodayOfMonthByWeek(String week, SimpleDateFormat pattern) {
        List<String> list = new ArrayList<String>();
        // 取得当前日期
        GregorianCalendar firstGc = (GregorianCalendar) Calendar.getInstance();
        firstGc.setTime(new Date());
        // 取得当前月的最后一天
        GregorianCalendar lastGc = (GregorianCalendar) Calendar.getInstance();
        lastGc.setTime(getLastDayOfMonth(new Date()));
        // 当前日期
        int today = firstGc.get(Calendar.DATE);
        // 取得当前月的所有天数
        int dayOfMonth = lastGc.get(Calendar.DAY_OF_MONTH);
        for (int i = today; i <= dayOfMonth; i++) {
            if (firstGc.get(Calendar.DAY_OF_WEEK) == ((Integer.valueOf(week) % 7) + 1)) {
                list.add(getDateFormat(firstGc.getTime(), pattern.toPattern()));
            }
            firstGc.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    // ===================================================================== //
    // 其他
    // ===================================================================== //

    /**
     * 将日期对象转换成为指定ORA日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
     * 
     * @param theDate 将要转换为字符串的日期对象。
     * @param hasTime 如果返回的字符串带时间则为true
     * @return 转换的结果
     */
    public static synchronized String toOraString(Date theDate, boolean hasTime) {
        // 1.如果有时间，则设置格式为getOraDateTimeFormat()的返回值
        // 2.否则设置格式为getOraDateFormat()的返回值
        // 3.调用toString(Date theDate, DateFormat theDateFormat)
        DateFormat theFormat;
        if (hasTime) {
            theFormat = getOraDateTimeFormat();
        } else {
            theFormat = getOraDateFormat();
        }
        return toString(theDate, theFormat);
    }

    /**
     * 将日期对象转换成为指定日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
     * 
     * @param theDate 将要转换为字符串的日期对象。
     * @param hasTime 如果返回的字符串带时间则为true
     * @return 转换的结果
     */
    public static synchronized String toString(Date theDate, boolean hasTime) {
        // 1.如果有时间，则设置格式为getDateTimeFormat的返回值
        // 2.否则设置格式为getDateFormat的返回值
        // 3.调用toString(Date theDate, DateFormat theDateFormat)
        DateFormat theFormat;
        if (hasTime) {
            theFormat = getDateTimeFormat();
        } else {
            theFormat = getDateFormat();
        }
        return toString(theDate, theFormat);
    }

    /**
     * 创建一个标准日期格式的克隆
     * 
     * @return 标准日期格式的克隆
     */
    public static synchronized DateFormat getDateFormat() {
        // 1.返回DATE_FORMAT
        SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT_SLASH_MM_DD_YYYY.clone();
        theDateFormat.setLenient(false);
        return theDateFormat;
    }

    /**
     * 创建一个标准时间格式的克隆
     * 
     * @return 标准时间格式的克隆
     */
    public static synchronized DateFormat getDateTimeFormat() {
        // 1.返回DATE_TIME_FORMAT
        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_FORMAT_SLASH_MM_DD_YYYY_HH_MM.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    /**
     * 创建一个标准ORA日期格式的克隆
     * 
     * @return 标准ORA日期格式的克隆
     */
    public static synchronized DateFormat getOraDateFormat() {
        // 1.返回ORA_DATE_FORMAT
        SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT_YYYY_MM_DD.clone();
        theDateFormat.setLenient(false);
        return theDateFormat;
    }

    /**
     * 创建一个标准ORA时间格式的克隆
     * 
     * @return 标准ORA时间格式的克隆
     */
    public static synchronized DateFormat getOraDateTimeFormat() {
        // 1.返回ORA_DATE_TIME_FORMAT
        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_FORMAT_YYYY_MM_DD_HH_MM.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    /**
     * 创建一个标准ORA时间格式的克隆
     * 
     * @return 标准ORA时间格式的克隆
     */
    public static synchronized DateFormat getOraDateTimeExtendedFormat() {
        // 1.返回ORA_DATE_TIME_EXTENDED_FORMAT
        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    /**
     * 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串，而不是一个空对象。
     * 
     * @param theDate 要转换的日期对象
     * @param theDateFormat 返回的日期字符串的格式
     * @return 转换结果
     */
    public static synchronized String toString(Date theDate, DateFormat theDateFormat) {
        // 1.theDate为空，则返回""
        // 2.否则使用theDateFormat格式化
        if (theDate == null) {
            return "";
        }
        return theDateFormat.format(theDate);
    }

    // ===============================//
    // 季度
    // ===============================//

    /**
     * 季度加法
     * 
     * @param inSeason 要转换的年+季度（20091：表示2009年第一季度）
     * @param n N季度
     * @return 转换结果（加了N季度后的20091）
     */
    public static String getAddNSeason(String inSeason, int n) {
        String outSeason = "";
        int year = 0;
        int season = 0;
        if (inSeason != null && inSeason.length() == 5) {
            year = Integer.parseInt(inSeason.substring(0, 4));
            season = Integer.parseInt(inSeason.substring(4, 5));
            if (n < 0) {
                year = year - (Math.abs(n) / 4);
                season = season - (Math.abs(n) % 4);
                if (season < 1) {
                    year = year - 1;
                    season = season + 4;
                }
            } else {
                year = year + (Math.abs(n) / 4);
                season = season + (Math.abs(n) % 4);
                if (season > 4) {
                    year = year + 1;
                    season = season - 4;
                }
            }
            outSeason = StringUtils.leftPad(String.valueOf(year), 4, "0") + String.valueOf(season);
        }
        return outSeason;
    }

    /**
     * 格式化年+季度
     * 
     * @param inSeason
     * @param patten yyyy年第s季度
     * @return
     */
    public static String formatYearAndSeason(String inSeason, String patten) {
        String outSeason = inSeason;
        if (patten == null || patten.trim() == "") {
            patten = "yyyy年s季度";
        }
        if (inSeason != null && inSeason.length() == 5 && (StringUtils.countMatches(patten, "yyyy") == 1)
                && (StringUtils.countMatches(patten, "s") == 1)) {
            outSeason = StringUtils.replace(patten, "yyyy", inSeason.substring(0, 4));
            outSeason = StringUtils.replace(outSeason, "s", inSeason.substring(4, 5));
        }
        return outSeason;
    }

    /**
     * 计算两个日期相差的年数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getYears(String strStartDate, String strEndDate) {
        int startYear = 0;
        int endYear = 0;
        if (StringUtils.length(strStartDate) < 4 || StringUtils.length(strEndDate) < 4) {
            return 0;
        }
        startYear = Integer.parseInt(StringUtils.substring(strStartDate, 0, 4));
        endYear = Integer.parseInt(StringUtils.substring(strEndDate, 0, 4));
        int difYear = endYear - startYear;
        return difYear;
    }

    /**
     * 计算两个日期相差的年数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getYears(Date strStartDate, Date strEndDate) {
        Calendar calendar = null;
        int startYear = 0;
        int endYear = 0;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startYear = calendar.get(Calendar.YEAR);

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endYear = calendar.get(Calendar.YEAR);

        int difYear = endYear - startYear;
        return difYear;
    }

    /**
     * 计算两个日期相差的月数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized int getMonths(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        int startYear = 0;
        int startMonth = 0;
        int endYear = 0;
        int endMonth = 0;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM.toPattern().equals(pattern.toPattern())) {
            // yyyyMM
            calendar = parseCalendarMonthFormat(strStartDate);
            startYear = calendar.get(Calendar.YEAR);
            startMonth = calendar.get(Calendar.MONTH) + 1;

            calendar = parseCalendarMonthFormat(strEndDate);
            endYear = calendar.get(Calendar.YEAR);
            endMonth = calendar.get(Calendar.MONTH) + 1;
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM
            calendar = parseCalendarMonthDashFormat(strStartDate);
            startYear = calendar.get(Calendar.YEAR);
            startMonth = calendar.get(Calendar.MONTH) + 1;

            calendar = parseCalendarMonthDashFormat(strEndDate);
            endYear = calendar.get(Calendar.YEAR);
            endMonth = calendar.get(Calendar.MONTH) + 1;
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM
            calendar = parseCalendarMonthSlashFormat(strStartDate);
            startYear = calendar.get(Calendar.YEAR);
            startMonth = calendar.get(Calendar.MONTH) + 1;

            calendar = parseCalendarMonthSlashFormat(strEndDate);
            endYear = calendar.get(Calendar.YEAR);
            endMonth = calendar.get(Calendar.MONTH) + 1;
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月
            calendar = parseCalendarMonthChineseFormat(strStartDate);
            startYear = calendar.get(Calendar.YEAR);
            startMonth = calendar.get(Calendar.MONTH) + 1;

            calendar = parseCalendarMonthChineseFormat(strEndDate);
            endYear = calendar.get(Calendar.YEAR);
            endMonth = calendar.get(Calendar.MONTH) + 1;
        } else {
            return 0;
        }

        int difMonth = (endYear - startYear - 1) * 12 + (12 - startMonth) + endMonth;

        return difMonth;
    }

    /**
     * 计算两个日期相差的月数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getMonths(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        int startYear = 0;
        int startMonth = 0;
        int endYear = 0;
        int endMonth = 0;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH) + 1;

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endYear = calendar.get(Calendar.YEAR);
        endMonth = calendar.get(Calendar.MONTH) + 1;

        int difMonth = (endYear - startYear - 1) * 12 + (12 - startMonth) + endMonth;

        return difMonth;

    }

    /**
     * 计算两个日期相差的天数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized int getDays(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM_DD.toPattern().equals(pattern.toPattern())) {
            // yyyyMMdd
            calendar = parseCalendarDayFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarDayFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM_DD.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM-dd
            calendar = parseCalendarDayDashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarDayDashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM_DD.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM/dd
            calendar = parseCalendarDaySlashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarDaySlashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM_DD.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月dd日
            calendar = parseCalendarDayChineseFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarDayChineseFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else {
            return 0;
        }

        int diffDay = (int) ((endMillis - startMillis) / (24 * 60 * 60 * 1000));

        return diffDay;
    }

    /**
     * 计算两个日期相差的天数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getDays(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startMillis = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endMillis = calendar.getTimeInMillis();

        int diffDay = (int) ((endMillis - startMillis) / (24 * 60 * 60 * 1000));

        return diffDay;
    }

    /**
     * 计算两个日期相差的小时数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized int getHours(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM_DD_HH.toPattern().equals(pattern.toPattern())) {
            // yyyyMMddHH
            calendar = parseCalendarHourFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarHourFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM_DD_HH.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM-dd HH
            calendar = parseCalendarHourDashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarHourDashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM_DD_HH.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM/dd HH
            calendar = parseCalendarHourSlashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarHourSlashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM_DD_HH.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月dd日 HH时
            calendar = parseCalendarHourChineseFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarHourChineseFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else {
            return 0;
        }

        int diffHour = (int) ((endMillis - startMillis) / (60 * 60 * 1000));

        return diffHour;
    }

    /**
     * 计算两个日期相差的小时数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getHours(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startMillis = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endMillis = calendar.getTimeInMillis();

        int diffHour = (int) ((endMillis - startMillis) / (60 * 60 * 1000));

        return diffHour;
    }

    /**
     * 计算两个日期相差的分钟数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized int getMinutes(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM.toPattern().equals(pattern.toPattern())) {
            // yyyyMMddHHmm
            calendar = parseCalendarMinuteFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMinuteFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM-dd HH:mm
            calendar = parseCalendarMinuteDashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMinuteDashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM/dd HH:mm
            calendar = parseCalendarMinuteSlashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMinuteSlashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月dd日 HH时mm分
            calendar = parseCalendarMinuteChineseFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMinuteChineseFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else {
            return 0;
        }

        int diffMinute = (int) ((endMillis - startMillis) / (60 * 1000));

        return diffMinute;
    }

    /**
     * 计算两个日期相差的分钟数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getMinutes(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startMillis = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endMillis = calendar.getTimeInMillis();

        int diffMinute = (int) ((endMillis - startMillis) / (60 * 1000));

        return diffMinute;
    }

    /**
     * 计算两个日期相差的秒数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized int getSeconds(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.toPattern().equals(pattern.toPattern())) {
            // yyyyMMddHHmmss
            calendar = parseCalendarSecondFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarSecondFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM-dd HH:mm:ss
            calendar = parseCalendarSecondDashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarSecondDashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM/dd HH:mm:ss
            calendar = parseCalendarSecondSlashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarSecondSlashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月dd日 HH时mm分ss秒
            calendar = parseCalendarSecondChineseFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarSecondChineseFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else {
            return 0;
        }

        int diffSecond = (int) ((endMillis - startMillis) / 1000);

        return diffSecond;
    }

    /**
     * 计算两个日期相差的秒数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized int getSeconds(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startMillis = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endMillis = calendar.getTimeInMillis();

        int diffSecond = (int) ((endMillis - startMillis) / 1000);

        return diffSecond;
    }

    /**
     * 计算两个日期相差的秒数
     * 
     * @param strStartDate
     * @param strEndDate
     * @param pattern
     * @return
     */
    public static synchronized long getMillis(String strStartDate, String strEndDate, SimpleDateFormat pattern) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (StringUtils.isEmpty(strStartDate) || StringUtils.isEmpty(strEndDate) || strStartDate.length() != strEndDate.length()) {
            return 0;
        }

        if (DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.toPattern().equals(pattern.toPattern())) {
            // yyyyMMddHHmmssSSS
            calendar = parseCalendarMilliFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMilliFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_DASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern().equals(pattern.toPattern())) {
            // yyyy-MM-dd HH:mm:ss,SSS
            calendar = parseCalendarMilliDashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMilliDashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS.toPattern().equals(pattern.toPattern())) {
            // yyyy/MM/dd HH:mm:ss,SSS
            calendar = parseCalendarMilliSlashFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMilliSlashFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else if (DateUtil.DATE_FORMAT_CHINESE_YYYY_MM_DD_HH_MM_SS_SSS.toPattern().equals(pattern.toPattern())) {
            // yyyy年MM月dd日 HH时mm分ss秒SSS毫秒
            calendar = parseCalendarMilliChineseFormat(strStartDate);
            startMillis = calendar.getTimeInMillis();
            calendar = parseCalendarMilliChineseFormat(strEndDate);
            endMillis = calendar.getTimeInMillis();
        } else {
            return 0;
        }

        long diffMilli = endMillis - startMillis;

        return diffMilli;
    }

    /**
     * 计算两个日期相差的秒数
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static synchronized long getMillis(Date strStartDate, Date strEndDate) {

        Calendar calendar = null;
        long startMillis = 0L;
        long endMillis = 0L;

        if (strStartDate == null || strEndDate == null) {
            return 0;
        }

        calendar = Calendar.getInstance();
        calendar.setTime(strStartDate);
        startMillis = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.setTime(strEndDate);
        endMillis = calendar.getTimeInMillis();

        long diffMilli = endMillis - startMillis;

        return diffMilli;
    }

    /**
     * 计算两个日期的季度差
     * 
     * @param strStartSeason (yyyyQ)
     * @param strEndSeason (yyyyQ)
     * @return
     */
    public static int getSeasons(String strStartSeason, String strEndSeason) {

        int difSeason = 0;

        if (StringUtils.isEmpty(strStartSeason) || StringUtils.isEmpty(strEndSeason) || strStartSeason.length() != 5 || strEndSeason.length() != 5) {
            return 0;
        }

        int startYear = Integer.parseInt(StringUtils.substring(strStartSeason, 0, 4));
        int startSeason = Integer.parseInt(StringUtils.substring(strStartSeason, 4, 6));
        int endYear = Integer.parseInt(StringUtils.substring(strEndSeason, 0, 4));
        int endSeason = Integer.parseInt(StringUtils.substring(strEndSeason, 4, 6));

        difSeason = (endYear - startYear - 1) * 4 + (4 - startSeason) + endSeason;

        return difSeason;
    }

    public static void main(String[] agrs) {
        // System.out.println(getDateYearFormat());
        // System.out.println(getDateMonthFormat());
        // System.out.println(getDateMonthDashFormat());
        // System.out.println(getDateMonthSlashFormat());
        // System.out.println(getDateDayFormat());
        // System.out.println(getDateDayDashFormat());
        // System.out.println(getDateDaySlashFormat());
        // System.out.println(getDateMinuteFormat());
        // System.out.println(getDateMinuteDashFormat());
        // System.out.println(getDateMinuteSlashFormat());
        // System.out.println(getDateSecondFormat());
        // System.out.println(getDateSecondDashFormat());
        // System.out.println(getDateSecondSlashFormat());
        // System.out.println(getDateMilliFormat());
        // System.out.println(getDateMilliDashFormat());
        // System.out.println(getDateMilliSlashFormat());
        // parseDateFormat("200901", "yyyyMM");
        System.out.println(getAddNSeason("20091", -5));
        System.out.println(getAddNSeason("20091", -2));
        System.out.println(getAddNSeason("20091", 0));
        System.out.println(getAddNSeason("20091", 2));
        System.out.println(getAddNSeason("20094", 5));
        System.out.println(formatYearAndSeason("20094", "yyyy年第s季度"));

        System.out.println((DateUtil.getAllDayOfMonthByWeek(DateUtil.parseDateMonthFormat("201106"), 0)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(4)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(1, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(2, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(3, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(4, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(5, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(6, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek(7, DATE_FORMAT_DASH_YYYY_MM_DD)));
        System.out.println((DateUtil.getAfterTodayOfMonthByWeek("01", DATE_FORMAT_DASH_YYYY_MM_DD)));

        System.out.println(DateUtil.getDateSecondDashFormat(DateUtil.getAddNHour(new Date(), 3)));
        System.out.println(DateUtil.getDateSecondDashFormat(DateUtil.getAddNMinute(new Date(), 3)));
        System.out.println(DateUtil.getDateSecondDashFormat(DateUtil.getAddNSecond(new Date(), 3)));

        System.out.println(DateUtil.getYears("201001", "201111"));
        System.out.println(DateUtil.getMonths("201001", "200911", DATE_FORMAT_YYYY_MM));
        System.out.println(DateUtil.getDays("20110715", "20110710", DATE_FORMAT_YYYY_MM_DD));
        System.out.println(DateUtil.getHours("2011071511", "2011071515", DATE_FORMAT_YYYY_MM_DD_HH));
        System.out.println(DateUtil.getSeasons("20101", "20092"));

        System.out.println((DateUtil.getAllDayOfMonthByWeek(DateUtil.parseDateMonthFormat("201109"), 3, DATE_FORMAT_DASH_YYYY_MM_DD)));

        System.out.println("==");
        System.out.println(DateUtil.getWeekOfDay());
        System.out.println("==");
        System.out.println("20120318:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120318")));
        System.out.println("20120319:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120319")));
        System.out.println("20120320:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120320")));
        System.out.println("20120321:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120321")));
        System.out.println("20120322:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120322")));
        System.out.println("20120323:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120323")));
        System.out.println("20120324:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120324")));
        System.out.println("20120325:" + DateUtil.getWeekOfDay(DateUtil.parseDateDayFormat("20120325")));
        System.out.println("==");
        System.out.println("20120318:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120318")));
        System.out.println("20120319:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120319")));
        System.out.println("20120320:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120320")));
        System.out.println("20120321:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120321")));
        System.out.println("20120322:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120322")));
        System.out.println("20120323:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120323")));
        System.out.println("20120324:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120324")));
        System.out.println("20120325:" + DateUtil.getDayOfWeek(DateUtil.parseDateDayFormat("20120325")));
        System.out.println("==");
        System.out.println("20120301:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120301")));
        System.out.println("20120302:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120302")));
        System.out.println("20120303:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120303")));
        System.out.println("20120304:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120304")));
        System.out.println("20120323:" + DateUtil.getWeekOfMonth());
        System.out.println("20120325:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120325")));
        System.out.println("20120331:" + DateUtil.getWeekOfMonth(DateUtil.parseDateDayFormat("20120331")));
        System.out.println("==");
        System.out.println("==");
        System.out.println("20120323:" + DateUtil.getDayOfMonth());
        System.out.println("20120101:" + DateUtil.getDayOfMonth(DateUtil.parseDateDayFormat("20120101")));
        System.out.println("20120301:" + DateUtil.getDayOfMonth(DateUtil.parseDateDayFormat("20120301")));
        System.out.println("==");
        System.out.println("20120323:" + DateUtil.getDayOfYear());
        System.out.println("20120101:" + DateUtil.getDayOfYear(DateUtil.parseDateDayFormat("20120101")));
        System.out.println("20120301:" + DateUtil.getDayOfYear(DateUtil.parseDateDayFormat("20120301")));
        System.out.println("==");
        System.out.println(DateUtil.getDateDayDashFormat(DateUtil.getLastDayOfNextWeek(new Date())));
        System.out.println(DateUtil.getDateDayDashFormat(DateUtil.getLastDayOfNextWeek2(new Date())));
    }
}
