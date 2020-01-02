package com.lance.study.exeicise;

import com.lance.study.model.user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LearnFunction {

  public static void main(String[] args) {
      float a = 0.9f-0.8f;
      float b = 0.3f-0.2f;
    System.out.println(Float.valueOf(a));
    System.out.println(Float.valueOf(b));
      User user3 = new User(0, "T", 0, 0);
    System.out.println(user3.hashCode());
    user3.setAge(1);
    System.out.println(user3.hashCode());

      List<User> list = new ArrayList<>();
      list.add(new User(1,"test",2,4));
      list.add(new User(2,"ty",5,6));
      list.add(new User(3,"uu",6,3));
      //consumer
      Consumer<User> c = x-> {
          if(x.getAge()==2){
              System.out.println(x);
          }
      };
      list.stream().forEach(c);
      list.stream().forEach(s->{
          if(s.getAge()==2){
              System.out.println(s);
          }
      });
    BiConsumer<User, User> biConsumer =
        ((user, user2) -> {
          System.out.println(user + "--" + user2);
          user.setAge(user2.getAge() + user.getAge());
          System.out.println(user + "==" + user2);
          System.out.println("===========");
        });
    User user1 =
        list.stream()
            .reduce(
                (a1, a2) -> {
                  User user = new User();
                  user.setAge(a1.getAge() + a2.getAge());
                  System.out.println(user.hashCode()+"====");
                  return user;
                })
            .get();
    System.out.println(user1);

//    list.stream().reduce(user,biConsumer,biConsumer);
  }
}
