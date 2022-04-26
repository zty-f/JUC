package com.zty.producerAndconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @version V1.0
 * @ClassName: com.zty.producerAndconsumer.BlockingQueueDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 21:11
 * @Description: 使用阻塞队列实现生产者消费者问题
 */

public class BlockingQueueDemo01 {

    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueue = new MyQueue();
        MyQueue myQueue1 = new MyQueue();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    myQueue.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();



        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    myQueue1.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}
class MyQueue{
    static BlockingQueue queue = new ArrayBlockingQueue<>(5);
    private static int number = 1;
    public synchronized void get() throws InterruptedException {
        String x = (String) queue.take();
        System.out.println(Thread.currentThread().getName()+"消费商品:================="+x);
    }

    public synchronized void put() throws InterruptedException {
        queue.put("商品"+number);
        System.out.println(Thread.currentThread().getName()+"生产者生产商品"+number++);
    }
}

