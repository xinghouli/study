package com.lance.study.model.user.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.beans.Transient;
import java.util.Objects;

//@Data

public class User extends Us{

    private  volatile int id;

    @NotBlank(message = "name不能为空")
    private String name;

    private int age;

    private int age2;

    public User(int id, String name, int age, int age2) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.age2 = age2;
    System.out.println("init2======");
    }

    public User(int id) {
        this.id = id;
    System.out.println("init1=====");
    }

    public User() {
    System.out.println("init");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
    System.out.println(-1.0F/0.0F);
    System.out.println(1.0F/0.0F);
    System.out.println(0.0F/0.0F);
    }

  public static void printUser(User user){
    System.out.println(user);
  }
}
