package com.zty.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version V1.0
 * @ClassName: com.zty.readwritelock.ReadWriteLockDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-25 19:45
 * @Description: 读写锁测试
 *
 * 读与写互斥   写与写互斥  读与读不互斥
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();

        //写入
        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(()->{
                myCache.put(String.valueOf(t),t);
            },String.valueOf(i)).start();
        }
        //读取
        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(()->{
                myCache.get(String.valueOf(t));
            },String.valueOf(i)).start();
        }
    }
}

/*
    自定义缓存  加锁
 */
class MyCache2{
    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存值  写操作 只希望同时一个线程写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();//写锁 不能读 不能其他线程写
        try {
            System.out.println(Thread.currentThread().getName()+"进行写操作：key="+key+",value="+value);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入数据完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取值  读操作
    public void get(String key){
        readWriteLock.readLock().lock(); //读的时候不允许写操作
        try {
            System.out.println(Thread.currentThread().getName()+"进行读操作：key="+key);
            Object o =  map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取数据完毕 value="+o.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/*
    自定义缓存 不加锁
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    //存值  写操作
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"进行写操作：key="+key+",value="+value);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入数据完毕！");
    }

    //取值  读操作
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"进行读操作：key="+key);
        Object o =  map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取数据完毕 value="+o.toString());
    }
}
