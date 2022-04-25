package com.zty.callable;

/**
 * @version V1.0
 * @ClassName: com.zty.callable.Runnable.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 16:16
 * @Description: 1
 */
public class RunnableTest {
    public static void main(String[] args) {
        MyT myT = new MyT();
        new Thread(myT,"A").start(); //同一个Runnable接口的实现类可以启动多次！
        new Thread(myT,"B").start();
        new Thread(myT,"C").start();
    }
}
class MyT implements Runnable{
    private volatile int i = 1;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+i++);
    }
}
