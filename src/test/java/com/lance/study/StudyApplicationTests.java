package com.lance.study;

import com.lance.study.model.user.dao.UserDao;
import com.lance.study.model.user.entity.User;
import com.lance.study.model.user.service.UserService;
import com.lance.study.offer.两个栈实现一个队列;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testDataSource() {
        DataSource bean = applicationContext.getBean(DataSource.class);
        System.out.println(bean.toString());
    }

    @Autowired
    private UserDao userDao;
    @Test
    public void testDao(){
        User user = new User();
        user.setAge(15);
        user.setName("uu");
        userDao.insert(user);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() throws Exception{
        long l = System.currentTimeMillis();
        for (int i = 0; i< 10; i++){
            User user = new User();
            user.setName(i+"");
            user.setAge(i);
            userService.insert(user);
        }
        System.out.println(System.currentTimeMillis()-l);
    }

    @Test
    public void testUserService2(){
        List list = new ArrayList<>();
        User u ;
        for (int i = 0; i< 100; i++){
            u = new User();
            u.setName(""+i);
            u.setAge(i);
            list.add(u);
        }
        long l1 = System.currentTimeMillis();
        userService.batchInsert0(list);
        System.out.println(System.currentTimeMillis()-l1+"----");
        long l = System.currentTimeMillis();
        List<String> strings = userService.batchInsert1(list);
        System.out.println(System.currentTimeMillis()-l);

    }

    @Test
    public void testSelect(){
        userDao.selectOne(10);
    }

}
