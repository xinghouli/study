package com.lance.study.scheduled;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

//    @Scheduled(cron = "0/1 * * * * ?")
//    @Async("taskExecutor")
    public void testScheduled(){
        System.out.println(Thread.currentThread().getId()+"-----------");
    }
}
