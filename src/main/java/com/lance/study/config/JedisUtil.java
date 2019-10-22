//package com.lance.study.config;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.lang.reflect.Field;
//
//@Component
//public class JedisUtil implements InitializingBean {
//
//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//    private JedisPool jedisPool;
//
//
//    Jedis getJedis() {
//        if (jedisPool != null) {
//            return jedisPool.getResource();
//        } else {
//            afterPropertiesSet();
//            return jedisPool.getResource();
//        }
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        Field poolField = ReflectionUtils.findField(JedisConnectionFactory.class, "pool");
//        ReflectionUtils.makeAccessible(poolField);
//        jedisPool = (JedisPool) ReflectionUtils.getField(poolField, jedisConnectionFactory);
//    }
//}
