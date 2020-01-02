package com.lance.study.exeicise;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Temp {

  static ExecutorService es = Executors.newFixedThreadPool  (
          7
  );
  public static void main(String[] args) {
    //1
    CountDownLatch latch  = new CountDownLatch(10);
    for(int i = 0; i < 10 ; i++){
      es.execute(() -> {
        //2
        System.out.println(Thread.currentThread().getName());
        f2(Thread.currentThread().getName());
        latch.countDown();
      });
    }
    try {
      latch.await();
      System.out.println("finish");
    } catch (InterruptedException e) {          }
  }

  public static void f2(String threadName){
    CountDownLatch latch  = new CountDownLatch(5);
    for(int i = 0 ; i < 5 ; i++){
      es.execute(
          () -> {
            System.out.println(threadName);
            System.out.println(Thread.currentThread().getName());
            latch.countDown();
          });
    }
    try {
      latch.await();
      System.out.println("finish2");
    } catch (InterruptedException e) {    }
  }


}
