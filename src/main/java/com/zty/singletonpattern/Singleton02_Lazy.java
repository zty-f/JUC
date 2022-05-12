package com.zty.singletonpattern;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 21:21
 * @Description: 懒汉单例模式  存在并发问题
 */
public class Singleton02_Lazy {

    private static Singleton02_Lazy instance;

    private Singleton02_Lazy(){ //构造器私有
        System.out.println(Thread.currentThread().getName()+"正在初始化单例！");
    }

    public static Singleton02_Lazy getInstance(){
        if(instance==null){
            instance = new Singleton02_Lazy();
        }
        return instance;
    }

    //单线程下ok,多线程会出问题

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton02_Lazy.getInstance();
            }).start();
        }
    }
}
