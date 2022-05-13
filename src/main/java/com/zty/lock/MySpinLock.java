package com.zty.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version V1.0
 * @ClassName: com.zty.lock.MySpinLock.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 20:47
 * @Description: 自定义一个自旋锁
 */
public class MySpinLock {

    // Thread 默认null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"===>MyLock!");

        // 自旋锁  已有线程占用，一直等待变成null才退出循环
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName()+"自旋过程等待锁···········");
        }
    }

    // 解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"===>MyUnLock!");
        atomicReference.compareAndSet(thread,null);
    }

}
