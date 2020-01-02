package com.lance.study.model.user.service;

import com.lance.study.common.utils.SpringContextHolder;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class TestServiceImpl implements TestService {

    @Override
//    @Transactional
    public void testService() {
    System.out.println("testService--1");
    System.out.println(TestServiceImpl.class.getName());
    TestServiceImpl bean = SpringContextHolder.getBean(TestServiceImpl.class);
    System.out.println(bean.getClass().getName());
    }

    @Transactional
    @Override
    public void testService2() {
    System.out.println("testService--2");
    System.out.println(this.getClass().getName());
    }
}
