package com.lance.study.exeicise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LearnThread {
    static volatile int i = 1;
    static int count = 100;

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread thread = new Thread() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    while (i < count){
                        if(i%2 == 0) {
                            System.out.println(Thread.currentThread().getName()+":"+i);
                            i++;
                            condition.signal();
                        } else {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    while (i < count){
                        if(i%2 == 1) {
                            System.out.println(Thread.currentThread().getName()+":"+i);
                            i++;
                            condition.signal();
                        } else {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        };
        thread1.start();
        thread.start();





    }
}


