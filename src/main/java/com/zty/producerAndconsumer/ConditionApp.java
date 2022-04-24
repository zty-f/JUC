package com.zty.producerAndconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: com.zty.producerAndconsumer.producer.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-23 21:03
 * @Description: JUC实现生产者消费者问题  condition实现精准通知线程
 */
public class ConditionApp {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

// 等待、业务、通知
class Data3{
    private int number = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public void printA(){
        lock.lock();
        try {
            //业务代码
            while (number!=1){
                condition1.await();
            }
            number = 2;
            condition2.signal();
            System.out.println(Thread.currentThread().getName()+"A--------->");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            //业务代码
            while (number!=2){
                condition2.await();
            }
            number = 3;
            condition3.signal();
            System.out.println(Thread.currentThread().getName()+"B--------->");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务代码
            while (number!=3){
                condition3.await();
            }
            number = 1;
            condition1.signal();
            System.out.println(Thread.currentThread().getName()+"C--------->");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
