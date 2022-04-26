package com.zty.synchronizedqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.synchronizedqueue.SynchronizedQueueDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 14:54
 * @Description: 同步队列测试
 */
public class SynchronizedQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"put a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"put a");
                synchronousQueue.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}
