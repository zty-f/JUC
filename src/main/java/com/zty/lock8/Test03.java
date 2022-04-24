package com.zty.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.lock8.Test01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-24 19:09
 * @Description: 锁的8个问题
 *
 * 先执行call
 * 再执行send
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(()->{
            phone1.send();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone3{
    //synchronized 锁的是方法的调用者！
    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信！");
    }
    public synchronized void call(){
        System.out.println("打电话！");
    }

    //没有锁，是同步方法
    public void hello(){
        System.out.println("hello！");
    }
}
