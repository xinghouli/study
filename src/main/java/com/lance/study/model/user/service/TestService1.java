package com.lance.study.model.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService1 {

    @Transactional
    public void testService1(){
    System.out.println("testService1");
        System.out.println(this.getClass().getName());
    }

    @Transactional
    public void testService2(){
    System.out.println("testService2");
        System.out.println(this.getClass().getName());

    }
}
