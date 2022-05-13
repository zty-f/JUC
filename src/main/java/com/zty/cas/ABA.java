package com.zty.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @ClassName: com.zty.cas.ABA.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 19:37
 * @Description: ABA问题
 *          A线程在修改数据的同时，B线程修改了数据又进行了还原数据，
 *          A线程在对比的时候发现内存值还是原来的值，继续进行更新，不会因为别人替换过而更新失败。
 */
public class ABA {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);//里面的值是int类型

        // ===================捣乱的线程==========================
        System.out.println(atomicInteger.compareAndSet(2022, 2024));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2024, 2022));
        System.out.println(atomicInteger.get());

        // ====================期望的线程==========================
        System.out.println(atomicInteger.compareAndSet(2022, 2025));
        System.out.println(atomicInteger.get());
    }
}
