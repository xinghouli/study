package com.lance.study.exeicise;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRateLimiter {

  public static void main(String[] args) {
      RateLimiter rateLimiter = RateLimiter.create(3.0);
      for (int i = 0; i< Integer.MAX_VALUE; i++){
          if(rateLimiter.tryAcquire()){
            System.out.println(i+"==="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
          }

      }
  }
}
