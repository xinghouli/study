package com.lance.study.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/concurrent/ThreadPoolTaskExecutor.html
 * https://blog.csdn.net/foreverling/article/details/78073105
 */
@Configuration
@EnableAsync
public class ThreadConfig {

    private static final ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * 无返回值的任务使用execute(Runnable)
     * 有返回值的任务使用submit(Runnable)
     *
     * 关闭线程池
     * 调用shutdown或者shutdownNow,两者都不会接受新的任务,而且通过要停止线程的interrupt方法来中断线程,有可能线程永远都不会中断
     * shoutdownnow会首先将线程池的状态设为stop,然后尝试停止所有线程(有可能导致部分任务没有执行完)然后返回未执行任务的列表.
     * 而shutdown则只是将线程池的状态设置为shutdown,然后中断所有没有执行任务的线程,并将剩余的任务执行完
     *
     */
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /**
         * 配置线程个数
         * 如果是CPU密集型任务,线程池的个数应该尽量少一些,一般为CPU个数+1条线程
         * 如果是IO密集型任务,那么线程池的线程可以放的很大 如2*CPU的个数
         * 对于混合型任务,能拆分的话，拆分成CPU密集型和IO密集型两种提高效率;不能拆分则根据实际情况调整
         */
        /** 自定义线程最小数量 */
        executor.setCorePoolSize(5);
        /** 自定义线程最大数量 */
        executor.setMaxPoolSize(30);
        /** 等待队列大小 */
        executor.setQueueCapacity(30);
        /** 保持等待时间 */
        executor.setKeepAliveSeconds(30);
        /**建议设置 方便问题排查 */
        executor.setThreadNamePrefix("coustomThreadPool");
        /**
         * RejectedExecutionHandler:线程池对拒绝任务的处理策略.ThredPoolExecutor定义了4种策略
         * 1 CallerRunsPolicy 重试当前任务直到成功
         * 2 AbortPolicy 抛弃任务,并抛出异常RejectedExecutionException
         * 3 DiscardPolicy 无声抛弃任务
         * 4 DiscardOldestPolicy  如果执行器未关闭 请求并重试任务, 丢弃最老的任务
         * 5 实现RejectExecutionHandler接口,自定义策略
         */
        executor.setRejectedExecutionHandler(callerRunsPolicy);
        executor.initialize();
        return executor;
    }


}
