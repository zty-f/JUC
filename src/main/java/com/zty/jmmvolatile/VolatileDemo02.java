package com.zty.jmmvolatile;

/**
 * @version V1.0
 * @ClassName: com.zty.jmmvolatile.VolatileDemo02.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 15:41
 * @Description: 验证原子性
 */
public class VolatileDemo02 {
    //volatile不保证原子性！！！！
    private volatile static int num = 0;//加了volatile不能保证原子性。

    public  static void add(){  //可以加synchronized实现原子性
        num++; //不是一个原子性操作 //使用  javap -c VolatileDemo02.class  命令可以把编译生成的文件反编译成字节码文件
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
