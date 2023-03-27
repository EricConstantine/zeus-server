package com.sgcc.code.common.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final int minutesOfseconds = 60;
    private static final int hourOfseconds = 60 * minutesOfseconds;
    private static final int dayOfseconds = 24 * hourOfseconds;
    private static final ZoneOffset defaultZoneOffset=OffsetDateTime.now().getOffset();
    private static final ZoneId defaultZone = ZoneId.systemDefault();

    /**
     * 获取当前时间（精确到秒）
     *
     * @return
     */
    public final static int getCurrentTime() {
        return Integer.parseInt(String.valueOf(System.currentTimeMillis() / 1000));
    }


    /**
     * 获取时间戳（精确到秒）
     *
     * @return
     */
    public final static int getTime(Date dateTime) {
        return Integer.parseInt(String.valueOf(dateTime.getTime() / 1000));
    }

    /**
     * 计算耗时
     *
     * @param startSeconds
     * @param endSeconds
     * @return
     */
    public final static String calculateTime(int startSeconds, int endSeconds) {
        int time = Math.abs(endSeconds - startSeconds); //精确到秒
        return calculateTime(time);
    }

    /**
     * 计算耗时
     *
     * @param seconds
     * @return
     */
    public final static String calculateTime(int seconds) {
        StringBuffer stringBuffer = new StringBuffer();
        if (seconds >= dayOfseconds) {
            int day = seconds / dayOfseconds;
            stringBuffer.append(day + "天");
            seconds = seconds % dayOfseconds;
        }
        if (seconds >= hourOfseconds) {
            int hour = seconds / hourOfseconds;
            stringBuffer.append(hour + "小时");
            seconds = seconds % hourOfseconds;
        }
        if (seconds >= minutesOfseconds) {
            int minutes = seconds / minutesOfseconds;
            stringBuffer.append(minutes + "分");
            seconds = seconds % minutesOfseconds;
        }
        stringBuffer.append(seconds + "秒");
        return stringBuffer.toString();
    }

    /**
     * 前几天的时间
     *
     * @param day
     * @return
     */
    public final static Date getTimeBeforeOfDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 0 - day);
        return calendar.getTime();
    }

    /**
     * 获取当前的年-月（在分表时用到）
     *
     * @return
     */
    public final static String getCurrentYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }

    /**
     * 日期转换为时间戳
     *
     * @return
     */
    public static int getTime(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return getTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将秒格式化为 date
     * @param seconds
     * @return
     */
    public static Date format(int seconds) {
        return new Date(Long.valueOf(seconds+"000"));
    }

    /**
     * 将秒格式化为 LocalDateTime
     * @param seconds
     * @return
     */
    public static LocalDateTime formatLocalDateTime(int seconds) {
        return LocalDateTime.ofInstant(format(seconds).toInstant(), defaultZone);
    }

    /**
     * 将时间格式化为 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime formatLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), defaultZone);
    }

    /**
     * 获取某个月的第一天时间(秒)
     * @param date
     * @return
     */
    public static int  getStartTimeOfMonth(Date date){
        LocalDateTime localDateTime=formatLocalDateTime(date);
        return getStartTime(localDateTime.getYear(),localDateTime.getMonthValue());
    }
    /**
     * 获取某天的开始时间(秒)
     * @param date
     * @return
     */
    public static int  getStartTimeOfDay(Date date){
        LocalDateTime localDateTime=formatLocalDateTime(date);
        return getStartTime(localDateTime.getYear(),localDateTime.getMonthValue(),localDateTime.getDayOfMonth());
    }

    /**
     * 获取某个月的最后一天时间(秒)
     * @param date
     * @return
     */
    public static int  getEndTimeOfMonth(Date date){
        LocalDateTime localDateTime=formatLocalDateTime(date);
        return getEndTime(localDateTime.getYear(),localDateTime.getMonthValue());
    }

    /**
     * 获取某天的结束时间(秒)
     * @param date
     * @return
     */
    public static int  getEndTimeOfDay(Date date){
        LocalDateTime localDateTime=formatLocalDateTime(date);
        return getEndTime(localDateTime.getYear(),localDateTime.getMonthValue(),localDateTime.getDayOfMonth());
    }

    /**
     * 时间戳转换为日期
     *
     * @return
     */
    public static String format(int seconds, String format) {
        if (format == null || "".equals(format)) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(format(seconds));
    }

    public static Date format(String dateStr, String format) throws Exception {
        if (format == null || "".equals(format)) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    public static String format(Date date,String format){
        if (format == null || "".equals(format)) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static int getStartTime(int year,int month){
        LocalDate firstday = LocalDate.of(year,month,1).with(TemporalAdjusters.firstDayOfMonth());
        LocalTime localTime=LocalTime.of(0,0,0);
        LocalDateTime localDateTime= LocalDateTime.of(firstday,localTime);
        return Long.valueOf(localDateTime.toEpochSecond(defaultZoneOffset)).intValue();
    }

    public static int getStartTime(int year,int month,int day){
        LocalDate firstday = LocalDate.of(year,month,day);
        LocalTime localTime=LocalTime.of(0,0,0);
        LocalDateTime localDateTime= LocalDateTime.of(firstday,localTime);
        return Long.valueOf(localDateTime.toEpochSecond(defaultZoneOffset)).intValue();
    }

    public static int getEndTime(int year,int month){
        LocalDate endDay = LocalDate.of(year,month,1).with(TemporalAdjusters.lastDayOfMonth());
        LocalTime localTime=LocalTime.of(23,59,59);
        LocalDateTime localDateTime= LocalDateTime.of(endDay,localTime);
        return Long.valueOf(localDateTime.toEpochSecond(defaultZoneOffset)).intValue();
    }

    public static int getEndTime(int year,int month,int day){
        LocalDate endDay = LocalDate.of(year,month,day);
        LocalTime localTime=LocalTime.of(23,59,59);
        LocalDateTime localDateTime= LocalDateTime.of(endDay,localTime);
        return Long.valueOf(localDateTime.toEpochSecond(defaultZoneOffset)).intValue();
    }


}
