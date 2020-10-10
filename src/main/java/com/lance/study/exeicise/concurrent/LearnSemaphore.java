package com.lance.study.exeicise.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class LearnSemaphore {

  public static void main(String[] args) throws Exception {
	  Semaphore semaphore = new Semaphore(3,false);
	  List<Thread> list = new ArrayList<>();
	  for (int i = 0; i<7;i++){
	  	Thread t = new Thread(()->{
		    try {
			    print(semaphore);
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
	    });
	  	list.add(t);
	  }
	  list.forEach(s->s.start());

  }

  public static void print(Semaphore semaphore) throws Exception{
	  semaphore.acquire();
	  System.out.println("ppppppppppp");
	  Thread.sleep(500);
  	semaphore.release();

  }
}
