package com.zty.lock;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.lock.DeadLockDemo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 21:18
 * @Description: 死锁测试
 */

//死锁排查    重点！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    /*
        1.使用 jsp -l 命令获取该类对应的线程号
        2.使用 jstack + 端口号  获取堆栈信息排查死锁位置

        下面为测试类死锁排查例子！！！！！
        ===================================================
"T2":
        at com.zty.lock.MyThread.run(DeadLockDemo01.java:47)
        - waiting to lock <0x000000076baa1898> (a java.lang.String)
        - locked <0x000000076baa18d0> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)
"T1":
        at com.zty.lock.MyThread.run(DeadLockDemo01.java:47)
        - waiting to lock <0x000000076baa18d0> (a java.lang.String)
        - locked <0x000000076baa1898> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.  //找到一个死锁
     */
public class DeadLockDemo01 {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyThread(lockA,lockB),"T1").start();
        new Thread(new MyThread(lockB,lockA),"T2").start();
        /*
            T1lock:lockA->get:lockB
            T2lock:lockB->get:lockA
         */
    }

}

class MyThread implements Runnable{
    private String lockA;
    private String lockB;

    public MyThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){

            System.out.println(Thread.currentThread().getName()+"lock:"+lockA+"->get:"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"lock:"+lockB+"->get:"+lockA);
            }
        }
    }
}
