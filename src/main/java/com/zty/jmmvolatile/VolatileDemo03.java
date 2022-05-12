package com.zty.jmmvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @ClassName: com.zty.jmmvolatile.VolatileDemo02.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 15:41
 * @Description: 使用原子类实现原子性增加
 *
 * 原子类底层都是直接和操作系统挂钩！  在内存中修改值！  Unsafe类 特殊！！！！
 *
 */
public class VolatileDemo03 {
    private volatile static AtomicInteger num = new AtomicInteger();//加了volatile不能保证原子性。

    public  static void add() {
        num.getAndIncrement();//使用CAS实现原子性操作
    }
    public static void main(String[] args) {

        //理论上结果为20000
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){ //保证上面线程运行完  默认main gc两个线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+":"+num);
    }
}
