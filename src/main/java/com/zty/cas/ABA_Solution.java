package com.zty.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version V1.0
 * @ClassName: com.zty.cas.ABA_Solution.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 20:01
 * @Description: ABA问题解决方法   添加版本号
 */
public class ABA_Solution {
    public static void main(String[] args) {
        //里面存储的是Integer类型，直接传入数字进行比较交换会产生错误，源码采用==进行比较，不能判断对象内部值是否相等，只能比较对象地址是否相等
        // 当数值范围在-127~128之间时，使用缓存，所以直接输入值获取的对象是相等的，超出范围每次传值都是new的新对象
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(99,1);

        new Thread(()->{
            int stamp = atomicInteger.getStamp(); //获得版本号
            System.out.println("A1==>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(atomicInteger.compareAndSet(99, 100, stamp, stamp + 1));
            stamp = atomicInteger.getStamp();
            System.out.println("A2==>"+stamp);

            System.out.println(atomicInteger.compareAndSet(100, 99, stamp, stamp + 1));
            System.out.println("A3==>"+atomicInteger.getStamp());
        },"A").start();

        new Thread(()->{
            int stamp = atomicInteger.getStamp(); //获得版本号
            System.out.println("B1==>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //此时版本不匹配，会更新失败
            System.out.println(atomicInteger.compareAndSet(99, 9999, stamp, stamp + 1));

            System.out.println("B2==>"+atomicInteger.getStamp());
        },"B").start();
    }
}
