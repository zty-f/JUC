package com.zty.demo01;

/**
 * @version V1.0
 * @ClassName: com.zty.demo01.Test01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-23 19:45
 * @Description: 线程由谁开启
 */
public class Test01 {
    public static void main(String[] args) {
        new Thread().start(); //调用本地方法private native void start0();来启动线程

        //获取cpu的核数
        //CPU密集型  IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

        //线程状态
        //1.Thread.State.NEW  新建
        //2.Thread.State.BLOCKED;  阻塞
        //3.Thread.State.RUNNABLE;  运行
        //4.Thread.State.TERMINATED;   终止
        //5.Thread.State.TIMED_WAITING;  超时等待
        //6.Thread.State.WAITING;  一直等待

        //wait()和sleep()区别
        /*
        1.来自不同的类
        2.wait会释放锁，sleep不会释放锁
        3.使用范围不同：sleep可以在任何地方   wait必须在同步代码块中
         */
    }
}
