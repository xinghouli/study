package com.lance.study.model.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lance.study.model.user.dao.UserDao;
import com.lance.study.model.user.entity.Us;
import com.lance.study.model.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        System.out.println(userDao);
        System.out.println(Thread.currentThread().getName());
    }


//    @Transactional(rollbackFor = Exception.class)
    public void batchInsert0(List<User> list){
        list.forEach(s->{
//            if(s.getAge()%5 != 0){
//                return;
//            }
            userDao.insert(s);
//            System.out.println(userDao);
//            System.out.println(Thread.currentThread().getName());
        });
    }

    public void batchInsert(List<User> list) {
        list.forEach(s->{
            taskExecutor.submit(()-> {
//                if(s.getAge()%5 != 0){
//                    throw new RuntimeException();
//                }
                try {
                    Thread.sleep(8888);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userDao.insert(s);
                System.out.println(Thread.currentThread().getName());
            });
            System.out.println("99999999999999999");
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
                System.out.println(userDao);
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

  public static void main(String[] args) throws Exception{
//      User user = new User();
//      user.setName("hhhh");
//      user.setAge(12);
//      user.setBirthday(new Date());
//      ObjectMapper objectMapper = new ObjectMapper();
//      String s = objectMapper.writeValueAsString(user);
//      Object o = objectMapper.readValue(s, new TypeReference<User>() {
//      });
//    System.out.println("====="+o);
//      System.out.println(s);
//      Map user1 = objectMapper.readValue(s, Map.class);
//    System.out.println(user1);
	  ObjectMapper objectMapper = new ObjectMapper();
	  String s = objectMapper.writeValueAsString(new Date());
    System.out.println(s);
  }
}
