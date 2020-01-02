package com.lance.study.aop.config;

import com.lance.study.model.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class TestUser {


    @Bean
    public User user(@Value("${id}")Integer id){
    System.out.println("======");
        return new User();
    }
    @Bean
    public User user(@Value("${id}")Integer id ,@Value("${name}") String name){

    System.out.println("--------");
        return new User(id);
    }

  public static void main(String[] args) {
    User u = new User(10);
      boolean instance = User.class.isInstance(1);
    System.out.println(instance);
    System.out.println(u);
    System.out.println(User.class.cast(u));
  }
}
