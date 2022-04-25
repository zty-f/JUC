package com.zty.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @ClassName: com.zty.countdownlatch.CountDownLatchTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 16:31
 * @Description: CountDownLatch测试  JUC辅助类
 *
 * 实现计数功能完成指定任务  减法计数器
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        //教学楼里面有8个学生在学习！
        CountDownLatch countDownLatch = new CountDownLatch(8);


        for (int i=1;i<=8;i++) {
            new Thread(()->{
                System.out.println("第"+ Thread.currentThread().getName()+"个学生离开教学楼！");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); //等待计数器归零然后继续执行！
        System.out.println("学生全部离开教学楼，关闭教学楼！");
    }
}
