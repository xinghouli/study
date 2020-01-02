package com.lance.study.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
* @see java.time.LocalDate
* @author lxh
* @version v1.0
* @return
* @since 2020-01-02
*/
public class LocalDateUtil {

    /**
     * 日期间隔
     * @param from
     * @param to
     * @return
     */
    public static long getDayBetween(LocalDate from, LocalDate to){
        return ChronoUnit.DAYS.between(from, to);
    }

    /**
     * 月份间隔
     * @param from
     * @param to
     * @return
     */
    public static long getMonthBetween(LocalDate from, LocalDate to){
        return ChronoUnit.MONTHS.between(from,to);
    }

    /**
     * 年份间隔
     * @param from
     * @param to
     * @return
     */
    private static long getYearBetween(LocalDate from, LocalDate to){
        return ChronoUnit.YEARS.between(from,to);
    }

    /**
     * 日期加减天数
     * @param localDate
     * @param days
     * @return
     */
    private static LocalDateTime plusDays(LocalDateTime localDate,long days){
        return localDate.plusDays(days);
    }

    /**
     * 日期加减月数
     * @param localDate
     * @param months
     * @return
     */
    private static LocalDate plusMonths(LocalDate localDate, long months){
        return localDate.plusMonths(months);
    }

    /**
     * 日期加减年数
     * @param localDate
     * @param years
     * @return
     */
    private static LocalDate plusYears(LocalDate localDate, long years){
        return localDate.plusYears(years);
    }

    /**
     * 日期加减周数
     * @param localDate
     * @param weeks
     * @return
     */
    private static LocalDate plusWeeks(LocalDate localDate, long weeks){
        return localDate.plusWeeks(weeks);
    }

  public static void main(String[] args) {
      LocalDate localDate = LocalDate.of(2019, 12, 25);

  }
}
