package com.zty.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: com.zty.lock.Demo02.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 20:37
 * @Description: 可重入锁
 * 指的是以线程为单位，当⼀个线程获取对象锁之后，
 * 这个线程可以再次获取本对象上的锁，⽽其他的线程是不可以的。
 * 指的是以线程为单位，当⼀个线程获取对象锁之后，这个线程可以再次获取本对象上的锁，⽽其他的线程是不可以的。
 *  ！！！！可重入锁的意义在于防止死锁。
 */

// Synchronized
public class Demo02 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(()->{
            phone.send(); //如果不是可重入锁则可能造成死锁
        },"A").start();

        new Thread(()->{
            phone.send();
        },"B").start();

    }
}
class Phone1{
    Lock lock = new ReentrantLock();
    public  void send(){
        lock.lock();//锁必须配对
        try {
            System.out.println(Thread.currentThread().getName()+"send");
            call();//这里也有锁
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public  void call(){
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+"call");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
