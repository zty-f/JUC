package com.zty.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.future.Demo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-27 15:33
 * @Description: 异步调用测试类  类似于Ajax
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //异步发起一个请求执行异步任务  没有返回值
        //CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
        //    //异步任务
        //    try {
        //        TimeUnit.SECONDS.sleep(5);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println(Thread.currentThread().getName()+"异步任务执行！");
        //});
        //completableFuture.get();//获取阻塞执行结果  等待异步任务执行完再继续执行下面代码
        //System.out.println("Main线程任务执行！");


        //有返回值的异步回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"异步任务执行！");
            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t,u)->{
            System.out.println("t=>"+t);//t 是异步任务返回值
            System.out.println("u=>"+u);//u 是异步任务的错误信息
        }).exceptionally((e)->{ //没有错误则不执行
            System.out.println(e.getMessage()); //获取错误的返回结果
            return 200;
        }).get());
        System.out.println("Main线程执行！！！");
    }
}
