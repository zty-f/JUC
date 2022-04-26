package com.zty.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.scene.input.KeyCode.T;

/**
 * @version V1.0
 * @ClassName: com.zty.threadpool.ExecutorsDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 15:13
 * @Description: Executors 工具类 3大方法
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//单线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(5);//固定容量线程池
        ExecutorService executorService = Executors.newCachedThreadPool();//可变大小的线程池 遇强则强(根据任务数量变化池子大小)
        //ExecutorService executorService = Executors.newScheduledThreadPool(10);//定时线程池


        try {
            for (int i = 0; i < 100; i++) {
                //使用线程池创建线程
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"执行！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完必须关闭
            executorService.shutdown();
        }
    }
}
