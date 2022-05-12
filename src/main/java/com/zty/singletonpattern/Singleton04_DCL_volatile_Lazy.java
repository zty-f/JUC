package com.zty.singletonpattern;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 21:37
 * @Description: 双重检测锁（DCL）+volatile下的懒汉单例模式  推荐使用
 */
public class Singleton04_DCL_volatile_Lazy {

    private static volatile Singleton04_DCL_volatile_Lazy instance;//避免指令重排序

    private Singleton04_DCL_volatile_Lazy(){ //构造器私有
        System.out.println(Thread.currentThread().getName()+"正在初始化单例！");
    }

    public static Singleton04_DCL_volatile_Lazy getInstance(){
        // 双重检测锁，DCL + volatile 懒汉式
        if (instance==null){
            synchronized (Singleton04_DCL_volatile_Lazy.class){
                if(instance==null){
                    instance = new Singleton04_DCL_volatile_Lazy();//不是原子性操作
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton04_DCL_volatile_Lazy.getInstance();
            }).start();
        }
    }
}
