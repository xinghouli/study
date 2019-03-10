package com.lance.study.monitor;

import java.lang.management.*;
import java.util.List;

public class ManagementMonitor {

    public static void main(String[] args) {
        management();
    }
    public static void management(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("=========程序运行参数========");
        //jvm启动参数
        System.out.println(runtimeMXBean.getInputArguments());
        //系统属性
        System.out.println(runtimeMXBean.getSystemProperties());
        //jvm名字
        System.out.println(runtimeMXBean.getVmName());
        System.out.println("==========线程状态=========");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //线程数量
        System.out.println(threadMXBean.getThreadCount());
        //如果启用CPU时间度量，则当前线程的总CPU时间
        System.out.println(threadMXBean.getCurrentThreadCpuTime());
        //如果启用了CPU时间度量，则当前线程的用户级CPU时间
        System.out.println(threadMXBean.getCurrentThreadUserTime());
        System.out.println("===========系统状态=========");
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        //CPU个数
        System.out.println(operatingSystemMXBean.getAvailableProcessors());
        //服务器平均负载数
        System.out.println(operatingSystemMXBean.getSystemLoadAverage());
        System.out.println("=========内存状态========");
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        //堆内存使用情况
        System.out.println(memoryMXBean.getHeapMemoryUsage().toString());
        //非堆使用情况
        System.out.println(memoryMXBean.getNonHeapMemoryUsage().toString());
        //堆内 各部分使用情况
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        memoryPoolMXBeans.forEach(pool-> {
            System.out.println(pool.getName());
            System.out.println(pool.getUsage());
        });
        System.out.println("==============GC状态===========");
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.forEach(collector->{
            System.out.println(collector.getName());
            System.out.println(collector.getCollectionCount());
            System.out.println(collector.getCollectionTime());
        });


    }
}
