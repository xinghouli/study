package com.lance.study.exeicise.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LearnCyclicBarrier {

  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier =
        new CyclicBarrier(
            5,
		        () -> {
              System.out.println("all   is   ok");
            });
	  List<Thread> threadList = new ArrayList<>();
	  Random random = new Random();
	  for(int i = 0 ; i <10 ; i++){
		  final int current = i;
	  	Thread t = new Thread(()->{
		    try {
			    Thread.sleep(random.nextInt(1000));
			    System.out.println("==========="+current);
			    cyclicBarrier.await();
		    } catch (BrokenBarrierException |InterruptedException e) {
			    e.printStackTrace();
		    }
	    });
	  	threadList.add(t);
	  }
	  threadList.forEach(s->s.start());
  }
}
