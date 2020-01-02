package com.lance.study.utils;

import java.time.*;
import java.util.Date;

/**
* @see java.util.Date
* @author lxh
* @version v1.0
* @since 2020-01-02
* @summary
*/
public class DateUtil {


    public static Date getDate(int year, Month month, int dayOfMonth){
        Instant instant = LocalDate.of(year, month, dayOfMonth).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second){
        Instant instant = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

  public static void main(String[] args) {
      LocalDateTime of = LocalDateTime.of(2019, 12, 11, 12, 10, 12);
    System.out.println(of);
    System.out.println(of.atZone(ZoneId.systemDefault()).toLocalDateTime());
      Instant instant = of.atZone(ZoneId.systemDefault()).toInstant();
      System.out.println(Date.from(instant));
  }
}
