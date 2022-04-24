package com.zty.demo01;

/**
 * @version V1.0
 * @ClassName: com.zty.demo01.SaleTicketDemo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-23 20:12
 * @Description: 卖票并发模拟
 */

/**
 * 真正的多线程开发，公司中的开发需要降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作！
 * 1. 属性、方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发： 多线程操作同一个资源类,把资源类丢入线程
        Ticket ticket = new Ticket();

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

// 资源类OOP
class Ticket{
    // 属性、方法
    private int number = 30;

    // 卖票的方式
    public synchronized void sale(){
        if (number > 0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，剩余："+number+"票");
        }
    }

}
