package com.lance.study.exeicise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LearnThread {
    public static void main(String[] args) throws Exception{
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Condition condition1 = reentrantLock.newCondition();
        Thread thread = new Thread() {
            @Override
            public void run() {
                reentrantLock.lock();
                for (int i = 0; i < 100; i++) {
                    if(i%2 == 0){
                        System.out.println(Thread.currentThread().getName()+":"+i);
                        condition1.signal();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }

                    }
                }
                reentrantLock.unlock();

            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                reentrantLock.lock();
                for (int i = 0; i < 100; i++) {
                    if(i%2 == 1){
                        System.out.println(Thread.currentThread().getName()+":"+i);
                       condition.signal();
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }
                reentrantLock.unlock();

            }
        };

        thread1.start();
        thread.start();
        thread.join();
        thread1.join();
    }
}


