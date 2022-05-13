package com.zty.singletonpattern;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 21:12
 * @Description: 饿汉单例模式  存在资源浪费问题
 */
public class Singleton01_Hungry {

    /*
    一上来就初始化，假如不使用以及实例占用内存很大会浪费不必要的资源,
    所以我们希望用到实例的时候才进行创建，节约资源，也就有了----懒汉单例模式！
     */
    private static final Singleton01_Hungry INSTANCE = new Singleton01_Hungry();

    //构造器私有化，单例模式特点！！！不允许多次实例化！
    private Singleton01_Hungry(){
        System.out.println(Thread.currentThread().getName()+"正在初始化单例！");
    }

    public static Singleton01_Hungry getInstance(){
        return INSTANCE;
    }
}
