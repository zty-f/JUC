package com.zty.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.blockingqueue.BlockingQueueDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 20:24
 * @Description: 阻塞队列测试类
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        //test01();

        //test02();

        //test03();

        test04();
    }

    //阻塞队列四组API

    /*
        1.抛出异常
     */
    public static void test01(){
        //队列大小为3
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);

        //添加
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));

        //System.out.println(queue.add(4));//超出大小范围 抛异常 java.lang.IllegalStateException: Queue full

        System.out.println("===================================");

        //删除
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        //System.out.println(queue.remove());//队列为空，没有元素可以删除 抛异常 java.util.NoSuchElementException

        //获取队首元素
        System.out.println(queue.element()); //队首元素不存在抛出异常 java.util.NoSuchElementException
    }

    /*
        2.有返回值，不抛出异常！
     */
    public static void test02(){
        //队列大小
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);

        //添加
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));

        System.out.println(queue.offer(4));//超出大小范围  返回false 不抛出异常

        System.out.println("======================================");

        //删除
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println(queue.poll()); //队列为空 没有元素可以删除 返回null 不抛出异常

        //获取队首元素
        System.out.println(queue.peek()); //队首元素不存在返回null  不抛出异常
    }

    /*
        3.阻塞等待  一直等待队列满足条件
     */
    public static void test03() throws InterruptedException {
        //队列大小
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);

        //添加 一直阻塞等待到可以添加元素
        queue.put(2);
        queue.put(2);
        queue.put(4);

        //queue.put(5);//超出大小范围  队列没有位置插入 一直阻塞等待

        System.out.println("======================================");

        //删除  一直阻塞等待到可以有元素进行删除
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());

        //System.out.println(queue.take()); //没有元素可以删除 一直阻塞等待
    }

    /*
        4.阻塞等待  超时退出
     */
    public static void test04() throws InterruptedException {
        //队列大小
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);

        //添加  超时退出
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));

        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS)); //等待两秒钟，还没有位置可以插入元素则退出操作继续执行后续代码

        System.out.println("======================================");

        //删除
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println(queue.poll(3,TimeUnit.SECONDS)); //等待3秒钟，还没有元素可以删除则退出操作继续执行后续代码

        System.out.println("程序结束！");
    }
}
