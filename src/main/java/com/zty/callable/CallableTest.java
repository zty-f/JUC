package com.zty.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @version V1.0
 * @ClassName: com.zty.callable.CallableTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 15:36
 * @Description: callable 测试
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new Runnable<>()).start();
        //new Thread(new FutureTask<>(Callable)).start();
        FutureTask task = new FutureTask<>(new MyThread());

        new Thread(task,"A").start();
        new Thread(task,"B").start();//这个不会执行，同一个task任务只执行一次！！

        Integer res = (Integer) task.get(); //获取Callable的返回接口
        System.out.println(res);
        Integer res1 = (Integer) task.get(); //可以重复获取，有缓存值
        System.out.println(res1);
    }
}

class MyThread implements Callable<Integer>{
    private volatile int x = 1;
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"call()");
        return ++x;
    }
}
