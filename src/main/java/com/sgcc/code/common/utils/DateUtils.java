package com.sgcc.code.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    /**
     * 添加时间秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date dayAddSeconds(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 某天转成时间戳
     *
     * @param date
     * @return
     */
    public static Long date2DayTimestamp(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dataTemp = dateFormatter.parse(dateFormatter.format(date));
            return dataTemp.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0l;
        }
    }

    public static long dateToDayL(Date date) {
        return date2DayTimestamp(date);
    }

    /**
     * 某天下一天转成时间戳
     *
     * @param date
     * @return
     */
    public static Long nextDay2DayTimestamp(Date date) {
        return date2DayTimestamp(addDay(date, 1));
    }

    /**
     * 增加一天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, day);
    }

    /**
     * 获取当前时间
     *
     * @return 20170604110922
     */
    public static String now2DateTime() {
        return now2CustomDate("yyyyMMddHHmmSS");
    }

    /**
     * 获取当前时间 yyyyMMdd
     *
     * @return 20170604
     */
    public static String now2ShortDate() {
        return now2CustomDate("yyyyMMdd");
    }

    /**
     * 获取当前时间 HHmmSS
     *
     * @return 110922
     */
    public static String now2ShortTime() {
        return now2CustomDate("HHmmSS");
    }

    /**
     * 获取当前时间
     *
     * @param format
     * @return
     */
    public static String now2CustomDate(String format) {
        return date2CustomDate(new Date(), format);
    }

    /**
     * 获取当前时间戳，单位毫秒
     *
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳，单位秒
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static Date yearBefore(Date source, int year) {
        return org.apache.commons.lang3.time.DateUtils.addYears(source, -year);
    }

    public static Date yearBefore(int year) {
        return org.apache.commons.lang3.time.DateUtils.addYears(new Date(), -year);
    }

    public static Date yearAfter(Date source, int year) {
        return org.apache.commons.lang3.time.DateUtils.addYears(source, year);
    }

    public static Date yearAfter(int year) {
        return org.apache.commons.lang3.time.DateUtils.addYears(new Date(), year);
    }

    public static Date monthBefore(Date source, int month) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(source, -month);
    }

    public static Date monthBefore(int month) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), -month);
    }

    public static Date monthAfter(Date source, int month) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(source, month);
    }

    public static Date monthAfter(int month) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), month);
    }

    public static Date dateBefore(int day) {
        return date(new Date(), -day);
    }

    public static Date dateBefore(Date source, int day) {
        return date(source, -day);
    }

    public static Date dateAfter(int day) {
        return date(new Date(), day);
    }

    public static Date dateAfter(Date source, int day) {
        return date(source, day);
    }

    private static Date date(Date source, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(source);
        cal.add(Calendar.DATE, day);
        Date date = cal.getTime();
        return date;
    }

    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, "yyyyMMddHHmmss");
    }

    public static Date strToDate(String dateStr, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyyMMddHHmmss");
    }

    public static String dateToStr(Date date, String format) {
        String result = "";
        if (date != null) {
            DateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }

    public static String date2CustomDate(Date date, String format) {
        return dateToStr(date, format);
    }

    public static String dateToStr(long time, String format) {
        return dateToStr(new Date(time), format);
    }

    public static Date dateToDay(Date date) {
        return new Date(DateUtils.date2DayTimestamp(date));
    }

    /**
     * 当前时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String now() {

        return now("yyyyMMddHHmmss");
    }

    /**
     * 当前时间
     *
     * @param format
     * @return
     */
    public static String now(String format) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return now.format(formatter);
    }

    /**
     * 当前时间+minutes后的时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String nowPlusMinutes(int minutes) {

        return nowPlusMinutes(minutes, "yyyyMMddHHmmss");
    }

    /**
     * 当前时间+minutes后的时间
     *
     * @param minutes
     * @param format
     * @return
     */
    public static String nowPlusMinutes(int minutes, String format) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newTime = now.plusMinutes(minutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return newTime.format(formatter);
    }

    /**
     * 某时间+minutes后的时间
     *
     * @param minutes
     * @return
     */
    public static String datePlusMinutes(Date date, int minutes) {

        return datePlusMinutes(date, minutes, "yyyyMMddHHmmss");
    }

    /**
     * 某时间+minutes后的时间
     *
     * @param minutes
     * @param format
     * @return
     */
    public static String datePlusMinutes(Date date, int minutes, String format) {

        LocalDateTime datetime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Shanghai"));
        LocalDateTime newTime = datetime.plusMinutes(minutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return newTime.format(formatter);
    }

    /**
     * 当前时间-minutes后的时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String nowMinusMinutes(int minutes) {

        return nowMinusMinutess(minutes, "yyyyMMddHHmmss");
    }

    /**
     * 当前时间-minutes后的时间
     *
     * @param minutes
     * @param format
     * @return
     */
    public static String nowMinusMinutess(int minutes, String format) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newTime = now.minusMinutes(minutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return newTime.format(formatter);
    }

    /**
     * 某时间-minutes后的时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String dateMinusMinutes(Date date, int minutes) {

        return dateMinusMinutess(date, minutes, "yyyyMMddHHmmss");
    }

    /**
     * 某前时间-minutes后的时间
     *
     * @param minutes
     * @param format
     * @return
     */
    public static String dateMinusMinutess(Date date, int minutes, String format) {

        LocalDateTime datetime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Shanghai"));
        LocalDateTime newTime = datetime.minusMinutes(minutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return newTime.format(formatter);
    }

    public static int countDayBetween(Date startDate, Date endDate) {
        //天数
        int days = 0;
        try {
            //将转换的两个时间对象转换成Calendard对象
            Calendar can1 = Calendar.getInstance();
            can1.setTime(startDate);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(endDate);
            can2.add(Calendar.DATE, 1);
            //拿出两个年份
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);

            Calendar can = null;
            //如果can1 < can2
            //减去小的时间在这一年已经过了的天数
            //加上大的时间已过的天数
            if (can1.before(can2)) {
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            } else {
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++) {
                //获取小的时间当前年的总天数
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                //再计算下一年。
                can.add(Calendar.YEAR, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 返回如yyyy-mm-dd格式日期,不足位年加20，月和日加0,如2013-01-01
     *
     * @return returnFormatStr分割的全格式日期，如2013-01-01
     * @parma dateStr 需要处理的日期
     * @parma formatStr 需要处理的日期的分隔符，不可为""
     * @parma returnFormatStr 需要返回的日期分隔符
     */
    public static String getFullDateStr(String dateStr, String formatStr, String returnFormatStr) {

        String[] s = null;
        s = dateStr.split(formatStr);
        if (s[0].length() == 2) {
            s[0] = "20" + s[0];
        }

        if (s[1].length() == 1) {
            s[1] = "0" + s[1];
        }

        if (s[2].length() == 1) {
            s[2] = "0" + s[2];
        }

        return s[0] + returnFormatStr + s[1] + returnFormatStr + s[2];
    }


    /**
     * 返回如23:03:03格式时间,不足位加0
     *
     * @return returnFormatStr分割的全格式时间，如23:03:03
     * @parma timeStr 需要处理的时间
     * @parma formatStr 需要处理的时间的分隔符，不可为""
     * @parma returnFormatStr 需要返回的时间分隔符，如":"
     */
    public static String getFullTimeStr(String timeStr, String formatStr, String returnFormatStr) {
        String[] s = null;
        String return_s = "";

        s = timeStr.split(formatStr);

        for (int i = 0; i < s.length; i++) {
            if (s[i].length() == 1) {
                s[i] = "0" + s[i];
            }
        }

        for (int i = 0; i < s.length; i++) {
            return_s += s[i] + (i == s.length - 1 ? "" : returnFormatStr);
        }


        return return_s;
    }

    /**
     * 传入字符串传出数字，传入为YYYY-MM-DD格式的日期，返回YYYYMMDD格式的数字
     *
     * @roseuid 41254983032C
     */
    public static long StrDateToInt(String strDate) {
        strDate = strDate.replaceAll("-", "");
        strDate = strDate.replaceAll(" ", "");
        strDate = strDate.replaceAll(":", "");
        return Long.parseLong(strDate);

    }

    public static long dateToInt(long time) {
        return StrDateToInt(dateToStr(time, "yyyy-MM-dd"));
    }

    public static long dateToInt(Date date) {
        return dateToInt(date.getTime());
    }

    /**
     * 判断某个日期是否在传入的时间段内，传入时间格式为YYYY-MM-DD
     */
    public static boolean isBetweenDate(String now, String start, String end) {
        long nowL = StrDateToInt(now);
        long startL = StrDateToInt(start);
        long endL = StrDateToInt(end);
        if (nowL >= startL && nowL <= endL)
            return true;
        else
            return false;
    }

    public static boolean isBetweenDate(String start, String end) {
        return isBetweenDate(dateToStr(new Date(), "yyyy-MM-dd"), start, end);
    }

    public static boolean isBetweenDate(long now, long start, long end) {
        String format = "yyyy-MM-dd";
        return isBetweenDate(dateToStr(now, format), dateToStr(start, format), dateToStr(end, format));
    }

    public static boolean isBetweenDate(long start, long end) {
        String format = "yyyy-MM-dd";
        return isBetweenDate(dateToStr(start, format), dateToStr(end, format));
    }

    public static boolean isBetweenDate(Date now, Date start, Date end) {
        String format = "yyyy-MM-dd";
        return isBetweenDate(dateToStr(now, format), dateToStr(start, format), dateToStr(end, format));
    }

    public static boolean isBetweenDate(Date start, Date end) {
        String format = "yyyy-MM-dd";
        return isBetweenDate(dateToStr(new Date(), format), dateToStr(start, format), dateToStr(end, format));
    }

    /**
     * 传入2个日期，判断是否第一个日期大于第二个日期
     * 传入日期格式 YYYY-MM-DD hh24:mi
     */
    public static boolean isDate1Big(String sDate, String eDate) {
        long intsDate = StrDateToInt(sDate);
        long inteDate = StrDateToInt(eDate);
        if (intsDate > inteDate)
            return true;
        else
            return false;
    }

    public static boolean isDateFuture(String sDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String nDate = df.format(date);

        return isDate1Big(sDate, nDate);
    }

    public static boolean isDateHistory(String sDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String nDate = df.format(date);

        return !isDate1Big(sDate, nDate);

    }

    /**
     * 获取两个时间之间的每一天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static List<Date> getFromAndTo(Date fromDate, Date toDate) {
        List<Date> dateList = new ArrayList<Date>();
        try {
            Calendar calendarTo = Calendar.getInstance();
            calendarTo.setTime(toDate);
            calendarTo.add(Calendar.DAY_OF_MONTH, 1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);
            while (calendar.getTime().before(calendarTo.getTime())) {
                dateList.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * date -> 周几	example: 二〇一八年四月十三日 15:24:49 -> "5"
     *
     * @param date
     * @return
     */
    public static String getWeekDay(Date date) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int index = c.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[index];
    }

    /**
     * date -> 周几	example: 二〇一八年四月十三日 15:24:49 -> "5"
     * 获得中文的周几
     *
     * @param date
     * @return
     */
    public static String getWeekDayString(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int index = c.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[index];
    }

    /**
     * 判断compareTime是否在startTime-endTime范围内
     *
     * @param compareTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeBetween(Date compareTime, Date startTime, Date endTime, boolean isIncludeBoundary) {
        if (compareTime.before(endTime) && compareTime.after(startTime)) {
            return true;
        } else if (isIncludeBoundary) {
            if (compareTime.equals(startTime) || compareTime.equals(endTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
        if (curTime == null || !curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }
        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

    }

    /**
     * n秒
     */
    public static long getSecond(int n) {
        return n * 1000l;
    }

    /**
     * n分
     */
    public static long getMinute(int n) {
        return n * getSecond(60);
    }

    /**
     * n小时
     */
    public static long getHour(int n) {
        return n * getMinute(60);
    }

    /**
     * n天
     */
    public static long getDay(int n) {
        return n * getHour(24);
    }

    /**
     * 获取传入时间0点的时间戳
     *
     * @param date
     * @return
     */
    public static Long getZeroDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    /**
     * 将时间戳转换成时间 时间格式 YYYY-MM-DD HH24:MI:SS
     *
     * @param millisecond
     * @return
     */
    public static String millisecondToDate(Long millisecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(millisecond));
        return date;
    }

    /**
     * 获取传入时间几周前或几周后的时间
     *
     * @param date  时间
     * @param count 几周后传正数，几周前传负数
     * @return
     */
    public static Date addWeeks(Date date, int count) {
        return org.apache.commons.lang3.time.DateUtils.addWeeks(date, count);
    }

    /**
     * 把分转成实际时间
     */
    public static Date minuteIntToDate(Date tempDate, Integer minuteInt) {
        Long tempDateL = date2DayTimestamp(tempDate);
        long minuteL = getMinute(minuteInt);
        return new Date(tempDateL + minuteL);
    }

    /**
     * 将时间戳转换成时间 时间格式 YYYY-MM-DD HH24:MI:SS:SSS
     *
     * @param millisecond
     * @return
     */
    public static String millisecondToDateStr(Long millisecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = sdf.format(new Date(millisecond));
        return date;
    }

    /**
     * 时间转换成当周的周一零点
     */
    public static long dateToMondayL(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getZeroDate(c.getTime());
    }

    /**
     * 时间转换成当周的周一零点
     */
    public static Date dateToMonday(Date date) {
        return new Date(dateToMondayL(date));
    }

    //时间转换成当周的周日零点
    public static long dateToSundayL(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getZeroDate(c.getTime());
    }

    //时间转换成当周的周一零点
    public static Date dateToSunday(Date date) {
        return new Date(dateToSundayL(date));
    }

    /**
     * 日期是否相等
     */
    public static boolean dayEquals(Date date1, Date date2) {
        String formatstr = "yyyy-MM-dd";
        String date1str = dateToStr(date1, formatstr);
        String date2str = dateToStr(date2, formatstr);
        return date1str.equals(date2str);
    }

    /**
     * 日期比较
     */
    public static boolean dayCompare(Date date1, String compare, Date date2) {
        long date1L = dateToDayL(date1);
        long date2L = dateToDayL(date2);
        if (">".equals(compare)) {
            return date1L > date2L;
        } else if (">=".equals(compare)) {
            return date1L >= date2L;
        } else if ("=".equals(compare) || "==".equals(compare)) {
            return date1L == date2L;
        } else if ("<=".equals(compare)) {
            return date1L <= date2L;
        } else if ("<".equals(compare)) {
            return date1L < date2L;
        }
        return false;
    }

    //是否为月初
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    //是否为月底
    public static boolean isLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 获取当天0点时间
     */
    public static Date getDayStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天23点59分59秒时间
     */
    public static Date getDayEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    //获取年初
    public static Date getYearStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取年底
    public static Date getYearEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    //获取月初
    public static Date getMonthStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取月底
    public static Date getMonthEnd(Date date) {
        Date date1 = monthAfter(date, 1);
        Date date2 = org.apache.commons.lang3.time.DateUtils.setDays(date1, 1);
        Date date3 = dateBefore(date2, 1);
        return getDayEndTime(date3);
    }

    public static String sqlDay(Date date) {
        return dateToStr(date, "yyyy-MM-dd");
    }

    public static String sqlDay(Long date) {
        return dateToStr(date, "yyyy-MM-dd");
    }

    public static String sqlSecond(Date date) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String sqlSecond(Long date) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String sqlMilliSecond(Date date) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String sqlMilliSecond(Long date) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

}
