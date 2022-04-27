package com.zty.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.jmm.JmmDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-27 16:46
 * @Description: JMM内存模型测试
 */
public class JmmDemo {
    private static volatile int num = 0;
    public static void main(String[] args) throws InterruptedException { //Main线程

        new Thread(()->{ //其他线程
            while (num==0){ //改线程也能及时获取num的最新值！
                System.out.println(num+"111");
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(111111);
        }).start();

        TimeUnit.SECONDS.sleep(1);

        num = 1;

        System.out.println(num);
    }
}
