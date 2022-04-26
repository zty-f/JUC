package com.zty.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.threadpool.ThreadPoolExecutorDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 15:37
 * @Description: 线程池七大参数
 */
        /*
        public ThreadPoolExecutor(  1.int corePoolSize, 核心线程数
                                    2.int maximumPoolSize, 最大线程数
                                    3.long keepAliveTime,  闲置线程（超过核心线程数的空闲的线程）存活时间
                                    4.TimeUnit unit,    非核心线程存活时间单位
                                    5.BlockingQueue<Runnable> workQueue,  阻塞队列类型
                                    6.ThreadFactory threadFactory,    线程工厂  创建线程的  一般不用动
                                    7.RejectedExecutionHandler handler   拒绝策略
                                 ) {
         */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //自定义线程池！ 工作中使用：ThreadPoolExecutor
        /*
              最大线程数如何定义： CPU核为 n 个
              1.CPU 密集型  n+1 保证CPU利用效率最高！
              2.IO 密集型   n*2 程序中十分耗费IO资源的线程数比较多。
         */

        //获取电脑最大线程数（CPU核数*2）代码！
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()  // 默认拒绝策略，任务数量>最大线程数+阻塞队列大小 抛出异常 java.util.concurrent.RejectedExecutionException
                //new ThreadPoolExecutor.CallerRunsPolicy()  // 哪个线程来的任务交给那个线程去执行此任务！
                //new ThreadPoolExecutor.DiscardPolicy()  // 自动拒绝任务，直接丢掉，不会抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，丢掉队列里面最老的任务，尝试提交新的任务去执行，不会抛出异常
        );


        try {
            for (int i = 0; i < 9; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"进入银行ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
