package com.zty.lock;

/**
 * @version V1.0
 * @ClassName: com.zty.lock.Demo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 20:30
 * @Description: 可重入锁
 */

// Synchronized
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.send();
        },"A").start();

        new Thread(()->{
            phone.send();
        },"B").start();

    }
}
class Phone{
    public synchronized void send(){
        System.out.println(Thread.currentThread().getName()+"send");
        call();//这里也有锁
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"call");
    }
}
