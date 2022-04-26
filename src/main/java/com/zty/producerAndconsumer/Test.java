package com.zty.producerAndconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 概述：
 * 总共能生产20次，消费20次
 * 但是队列中的存在的数量最多有10个
 * 注意：
 * 标记一：为了直观的看到队列满了，需要消费了，但是队列自己会阻塞我们无需进行通知
 * 标记二：也是为了直观的看到队列在阻塞
 */
public class Test {
    public static void main(String[] args) {
        BlockingQueue<Integer> breadqueen = new ArrayBlockingQueue<>(10);
        /**
         * 生产者
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Thread.currentThread().getName()) {
                    for (int i = 0; i < 20; i++) {
                        try {
                            breadqueen.put(i);
                            System.out.println(Thread.currentThread().getName() + "生产+1---放入" + i);
                            if (breadqueen.size() == 10) {//标记一：；这里10和队列的capacity相同
                                System.out.println("当前队列" + breadqueen.size() + "满了，开始消费");//这里由capacity决定最大值
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "生产者").start();
        /**
         * 消费者
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Thread.currentThread().getName()) {
                    for (int i = 0; i < 20; i++) {
                        try {
                            Integer takenum = breadqueen.take();
                            System.out.println(Thread.currentThread().getName() + "消费-1---取走" + takenum);
                            //Thread.sleep(100);//标记二：让消费者线程睡一会，更佳体现队列的阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "消费者").start();
    }
}
