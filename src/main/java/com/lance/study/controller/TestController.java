package com.lance.study.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lance.study.model.user.entity.User;
import com.lance.study.model.user.service.TestService;
import com.lance.study.model.user.service.TestService1;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/test")
@Validated
public class TestController {

    @RequestMapping(value = "/1",method = RequestMethod.POST)
    public String test(@RequestBody Integer param){
        log.info("param={}",param);
        return "sdhfsjd";
    }

  public static void main(String[] args) throws Exception{
    System.out.println(JSON.toJSONString(LocalDate.now()));
//      List<User> users = Arrays.asList(new User(1, "test", 1, 3), new User(2, "r", 4, 3));
//      Optional<User> reduce = users.stream().reduce((u1, u2) -> {
//          u1.setAge(u1.getAge() + u2.getAge());
//          return u1;
//      });
//    System.out.println(reduce.orElse(new User()));
  }

    @GetMapping(value = "/1")
    public LocalDateTime getNow(){
        return LocalDateTime.now();
    }

    @GetMapping(value = "/2")
    public LocalDate getLocalDate(){
        return LocalDate.now();
    }

    @PostMapping(value = "/3")
    public LocalDate get3(@RequestParam("localDate") LocalDate localDate){
        return localDate;
    }

    @PostMapping(value = "/4")
    public LocalDateTime get4(@RequestParam("localDateTime") LocalDateTime localDateTime){
        return localDateTime;
    }
    @PostMapping(value = "/5")
    public TestLocaldate get5(@RequestBody TestLocaldate testLocaldate){
        return testLocaldate;
    }

    @PostMapping(value = "/6")
    public void t(@RequestBody U u){
    System.out.println(u);
    }

    @Data
    static class U {
//        @JsonProperty("name_2")
        @JsonAlias(value = {"name_1","name_2"})
        String name;
        Date date;
    }
    @Data
    static class TestLocaldate{
        LocalDate localDate = LocalDate.now();

        LocalDateTime localDateTime = LocalDateTime.now();
    }
    @Data
    static class User1{
        private String na;
        private Integer age;
    }
    @Autowired
    private TestService testServiceImpl;
    @Autowired
    private TestService1 testService1;

    @GetMapping(value = "/t1")
    public void t1(){
        testServiceImpl.testService();
    }

    @GetMapping(value = "/t2")
    public void t2(){
        testService1.testService1();
    }
    @PostMapping(value = "/insert")
    public void insertUser(@RequestBody @Valid User u){
        System.out.println(u);
    }

    @GetMapping(value = "/get")
    public Integer get(@RequestParam("num") @Valid @NotNull(message = "num不能为空") Integer num){
        return num;
    }

}
