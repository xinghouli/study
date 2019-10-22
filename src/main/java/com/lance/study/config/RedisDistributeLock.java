//package com.lance.study.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import java.util.Collections;
//import java.util.concurrent.Executor;
//
//@Component
//@Slf4j
//public class RedisDistributeLock {
//
//    @Autowired
//    private JedisUtil jedisUtil;
//    @Autowired
//    private Executor executor;
//
//    private static final String SETNX_OK = "OK";
//    private static final Long EVAL_SUCCESS = 1L;
//
//    private static final String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "PX";
//
//    public boolean getLock(String key, String value, int seconds) {
//        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
//            return false;
//        }
//        if (seconds < 30) {
//            seconds = 30;
//        }
//
//        try (Jedis jedis = jedisUtil.getJedis()) {
//            String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds * 1000);
//            return SETNX_OK.equalsIgnoreCase(result);
//        } catch (Exception e) {
//            log.error("分布式锁加锁出现异常：{}", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//        return false;
//    }
//
//    public boolean releaseLock(String key, String value) {
//
//        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
//            return false;
//        }
//
//        try (Jedis jedis = jedisUtil.getJedis()) {
//            Object result = jedis.eval(UNLOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
//            return EVAL_SUCCESS.equals(result);
//        } catch (Exception e) {
//            log.error("分布式锁解锁出现异常：{}", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//        return false;
//    }
//
//    public void extensionExpireTime(String key, String value, int seconds) {
//        if (seconds <= 0 || StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
//            return;
//        }
//
//        Thread extension = new Extension(key, value, seconds);
//        extension.setDaemon(true);
//        executor.execute(extension);
//    }
//
//    public Long releaseLock(String key) {
//        try (Jedis jedis = jedisUtil.getJedis()) {
//            return jedis.del(key);
//        } catch (Exception e) {
//            log.error("分布式锁解锁出现异常：{}", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//        return -1L;
//    }
//
//    class Extension extends Thread {
//
//        private String key;
//        private String value;
//        private Integer seconds;
//
//        Extension(String key, String value, Integer seconds) {
//            this.key = key;
//            this.value = value;
//            this.seconds = seconds;
//        }
//
//        @Override
//        public void run() {
//            try (Jedis jedis = jedisUtil.getJedis()) {
//                int counter = 0;
//                log.info(Thread.currentThread().isInterrupted() + "");
//                while (!Thread.currentThread().isInterrupted() && value.equals(jedis.get(key))) {
//                    log.info("守护线程运行中...");
//                    Long ttl = jedis.ttl(key);
//                    if (ttl < seconds) {
//                        log.info("开始第{}次续期，时间：{}秒", ++counter, seconds);
//                        jedis.expire(key, seconds);
//                        Thread.sleep(seconds * 1000 - 10 * 1000);
//                    }
//                }
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//    }
//}
