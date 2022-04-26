package com.zty.producerAndconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: com.zty.producerAndconsumer.BlockingQueueDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 21:11
 * @Description: 使用阻塞队列实现生产者消费者问题
 */

public class  BlockingQueueDemo{

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<>(5);
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+"生产者生产商品"+i);
                    queue.put("商品"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 11; i <= 20; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+"生产者生产商品"+i);
                    queue.put("商品"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    String x = (String) queue.take();
                    System.out.println(Thread.currentThread().getName()+"消费商品:================="+x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        },"C").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    String x = (String) queue.take();
                    System.out.println(Thread.currentThread().getName()+"消费商品:================="+x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        },"D").start();

    }
}

