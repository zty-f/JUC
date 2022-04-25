package com.zty.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.semaphore.SemaphoreTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 17:28
 * @Description: Semaphore(信号量)  JUC辅助类测试  通常用来限流操作！
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //线程数量： 停车位
        Semaphore semaphore = new Semaphore(5);//限制线程数量，每次执行多少个线程，排队轮流执行，相当于一个流量窗口
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//获取信号量 （获取停车位）
                    System.out.println(Thread.currentThread().getName()+"抢到停车位！");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();  //释放信号量 （离开停车位）
                }
            },String.valueOf(i)).start();
        }
    }
}
