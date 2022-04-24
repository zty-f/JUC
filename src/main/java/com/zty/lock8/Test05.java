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
 * 先执行send
 * 再执行call
 */
public class Test05 {
    public static void main(String[] args) throws InterruptedException {
        Phone5 phone = new Phone5();
        Phone5 phone1 = new Phone5();

        new Thread(()->{
            phone.send();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone1.call();
        },"B").start();
    }
}

class Phone5{
    //synchronized 锁的是方法的调用者！
    public static synchronized void send(){ //static方法锁类对象 Class对象
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信！");
    }
    //锁调用者
    public synchronized void call(){
        System.out.println("打电话！");
    }
}
