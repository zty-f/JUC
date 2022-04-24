package com.zty.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.lock8.Test01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-24 19:09
 * @Description: 锁的8个问题
 * 先执行send  再执行call
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(()->{
            phone.send();  //和下面是同一个调用者，只有一个锁对象，谁先获得锁谁先执行。
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}
class Phone{
    //synchronized 锁的是方法的调用者！
    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信！");
    }
    public synchronized void call(){
        System.out.println("打电话！");
    }
}
