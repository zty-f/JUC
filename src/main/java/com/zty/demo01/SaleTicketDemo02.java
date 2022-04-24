package com.zty.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: com.zty.demo01.SaleTicketDemo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-23 20:12
 * @Description: 卖票并发模拟
 */

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 并发： 多线程操作同一个资源类,把资源类丢入线程
        Ticket1 ticket = new Ticket1();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}

// Lock
class Ticket1{
    // 属性、方法
    private int number = 30;

    Lock lock = new ReentrantLock();//默认非公平锁

    // 卖票的方式
    public synchronized void sale(){
        lock.lock(); //加锁

        try {
            //业务代码
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，剩余："+number+"票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //解锁
        }

    }

}
