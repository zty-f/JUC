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
    public volatile static  BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
    public static boolean flag=true;
    class Producer implements Runnable {
        @Override
        public  void run() {
            while(flag){//到了时间之后生产者停止生产
                try {
                    blockingQueue.put(1);
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + blockingQueue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产者停止生产");
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while(true){
                try {
                    //blockingQueue.take();
                    Integer res=blockingQueue.poll(2, TimeUnit.SECONDS);
                    if(res==null){
                        System.out.println("超过两秒没有取到数据，消费者即将退出");
                        return ;
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + blockingQueue.size());
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo test1=new BlockingQueueDemo();
        new Thread(test1.new Producer(),"A").start();
        new Thread(test1.new Consumer(),"B").start();
        new Thread(test1.new Producer(),"C").start();
        new Thread(test1.new Consumer(),"D").start();
        new Thread(test1.new Producer(),"E").start();
        // new Thread(test1.new Consumer()).start();
        Thread.sleep(3000);
        flag=false;

    }
}