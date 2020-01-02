package com.lance.study.aop.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JSONObject commonException(Exception e){
        JSONObject jsonObject = new JSONObject();
        log.error("异常信息{}",e);
        jsonObject.put("code",0);
        jsonObject.put("message",e.getMessage());
        return jsonObject;
    }
}
