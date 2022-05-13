package com.zty.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: com.zty.lock.MySpinLockTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 20:58
 * @Description: 自定义自旋锁测试
 */
public class MySpinLockTest {
    public static void main(String[] args) throws InterruptedException {
        // 原来使用的锁
        //ReentrantLock reentrantLock = new ReentrantLock();
        //reentrantLock.lock();
        //reentrantLock.unlock();


        // 自己的 底层使用自旋锁
        MySpinLock mySpinLock = new MySpinLock();

        new Thread(()->{ //A线程先获得锁
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                mySpinLock.myUnLock();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{ // B线程自旋等待A线程释放锁再获得锁
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                mySpinLock.myUnLock();
            }
        },"B").start();
    }
}
