package com.zty.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @ClassName: com.zty.cas.CASDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 19:12
 * @Description: CAS理解学习
 * CAS∶比较当前工作内存中的值和主内存中的值，如果这个值是期望的，那么则执行操作!如果不是就一直循环!
 */
public class CASDemo {

    //Unsafe类  Java操作内存的一个途径   内部调用c++native（本地）方法

    // CAS  compare and swap：比较和交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);

        // 源码: public final boolean compareAndSet(int expect, int update) 1.期望值 2.更新值
        // 如果内存中的值等于传入期望值，那么就进行更新，否则不更新
        System.out.println(atomicInteger.compareAndSet(2022, 2024));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2022, 2025));
        System.out.println(atomicInteger.get());

        //CAS 底层自旋锁
        /*
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));//没有更新成功一直循环更新
         */

        // CAS缺点
        /*
            1、 循环会耗时
            2、 一次性只能保证一个共享变量的原子性
            3、 ABA问题
         */
    }
}
