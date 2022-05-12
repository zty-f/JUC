package com.zty.jmmvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.jmm.JmmDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-27 16:46
 * @Description: JMM内存模型测试   验证volatile可见性
 */
public class JmmDemo {
    //不加volatile就会死循环
    private static volatile int num = 0;//加上volatile可以使得num可见
    public static void main(String[] args) throws InterruptedException { //Main线程

        new Thread(()->{ //其他线程
            while (num==0){ //该线程对主内存值变化不知晓
                //System.out.println("num==0"); //里面添加输出语句也能获取最新num值，因为输出函数内部实现了锁，可以获取最新内存值
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println("num=="+num);
    }
}
