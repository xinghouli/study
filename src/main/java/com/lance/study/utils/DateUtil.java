package com.lance.study.utils;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
* @see java.util.Date
* @author lxh
* @version v1.0
* @since 2020-01-02
* @summary
*/
public class DateUtil {

    /**
     * 获取年月日
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    public static Date getDate(int year, int month, int dayOfMonth){
        Instant instant = LocalDate.of(year, month, dayOfMonth).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 年月日时分秒
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second){
        Instant instant = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }


    public static LocalDateTime dateToLocalDateTime(Date date){
       return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 日加减
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, long days){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime plusDays = localDateTime.plusDays(days);
        return localDateTimeToDate(plusDays);
    }

    /**
     * 月份加减
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(Date date, long months){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime plusMonths = localDateTime.plusMonths(months);
        return localDateTimeToDate(plusMonths);
    }

    /**
     * 获取月初
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
        LocalDateTime with = localDateTime.with(temporalAdjuster);
        return localDateTimeToDate(with);
    }

    /**
     * 获取月末
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.lastDayOfMonth();
        LocalDateTime with = localDateTime.with(temporalAdjuster);
        return localDateTimeToDate(with);
    }

    /**
     * 获取日期的周一
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
        LocalDateTime with = localDateTime.with(temporalAdjuster);
        return localDateTimeToDate(with);
    }

    /**
     * 获取日期的周末
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY);
        LocalDateTime with = localDateTime.with(temporalAdjuster);
        return localDateTimeToDate(with);
    }

	/**
	 * date >= originDate
	 * @param originDate
	 * @param date
	 * @return
	 */
	public static boolean isAfterAndEqual(Date originDate, Date date){
        LocalDateTime originLocalDateTime = dateToLocalDateTime(originDate);
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return (localDateTime.isAfter(originLocalDateTime) || localDateTime.isEqual(originLocalDateTime));
    }

	/**
	 * date <= originDate
	 * @param originDate
	 * @param date
	 * @return
	 */
	public static boolean isBeforeAndEqual(Date originDate, Date date){
        LocalDateTime originLocalDateTime = dateToLocalDateTime(originDate);
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return (localDateTime.isBefore(originLocalDateTime) || localDateTime.isEqual(originLocalDateTime));
    }





  public static void main(String[] args) {
      Date dateTime = getDateTime(2019, 12, 21, 10, 20, 30);


      Date dateTime1 = getDateTime(2020, 1, 3, 5, 10, 30);
//      Date dateTime2 = getDateTime(2020, 1, 3, 5, 10, 30);
    System.out.println(isBeforeAndEqual(dateTime,dateTime1));
    System.out.println(isAfterAndEqual(dateTime,dateTime1));
//      TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
//      LocalDate with = localDateTime.with(temporalAdjuster);
//    System.out.println(with);
//      TemporalAdjuster temporalAdjuster1 = TemporalAdjusters.lastDayOfMonth();
//      LocalDate with1 = localDateTime.with(temporalAdjuster1);
//      System.out.println(with1);
//      TemporalAdjuster next = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
//      LocalDate with2 = localDateTime.with(next);
//    System.out.println(with2);
//      TemporalAdjuster next1 = TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY);
//      LocalDate with3 = localDateTime.with(next1);
//    System.out.println(with3);
//      TemporalAdjusters.previous()
  }
}
