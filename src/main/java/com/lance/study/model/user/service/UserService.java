package com.lance.study.model.user.service;

import com.lance.study.model.user.dao.UserDao;
import com.lance.study.model.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Async("taskExecutor")
    public void insert(User user) {
        userDao.insert(user);
        System.out.println(Thread.currentThread().getName());
    }


    @Transactional(rollbackFor = Exception.class)
    public void batchInsert0(List<User> list){
        list.forEach(s->{
            if(s.getAge()%5 != 0){
                return;
            }
            userDao.insert(s);
            System.out.println(Thread.currentThread().getName());
        });
    }

    public void batchInsert(List<User> list){
        list.forEach(s->{
            taskExecutor.execute(()-> {
                if(s.getAge()%5 != 0){
                    throw new RuntimeException();
                }
                userDao.insert(s);
                System.out.println(Thread.currentThread().getName());
            });
        });
    }

    public List<String> batchInsert1(List<User> list){
        List<String> l = new ArrayList<>();
        list.forEach(s->{
            Future<String> submit = taskExecutor.submit(() -> {
                if(s.getAge()%5 != 0){
                    return "fail";
                }
                userDao.insert(s);
                System.out.println(Thread.currentThread().getName());
                return "OK";
            });
            try {
                l.add(submit.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return l;
    }

}
