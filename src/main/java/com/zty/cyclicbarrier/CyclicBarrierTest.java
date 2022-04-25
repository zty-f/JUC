package com.zty.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @version V1.0
 * @ClassName: com.zty.cyclicbarrier.CyclicBarrierTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 16:44
 * @Description:
 * 加法计数器  JUC辅助类
 */
/////  final int finalI = i;  //lambda表达式默认不能获取前面参数，可以创建一个final类型的副本供lambda表达式内部使用！
public class CyclicBarrierTest {
    /*
        集齐七颗龙珠召唤神龙
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七科龙珠，成功召唤神龙！");
        });//可以多次使用

        for (int i = 1; i <= 7; i++) {
            final int finalI = i;  //lambda表达式默认不能获取前面参数，可以创建一个final类型的副本供lambda表达式内部使用！
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集到"+ finalI +"颗龙珠！");
                try {
                    cyclicBarrier.await();//等待 没有达到指定数量就一直阻塞！  线程等待数量达到7，结束阻塞，执行对应完成时代码，然后继续执行其他业务代码  并重置cyclicBarrier的线程数为0
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI);
                try {
                    cyclicBarrier.await();//等待  线程等待数量达到7，结束阻塞，执行对应完成时代码，然后继续执行其他业务代码 并重置cyclicBarrier的线程数为0
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
