package com.zty.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version V1.0
 * @ClassName: com.zty.collection.ListTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-24 19:55
 * @Description: 集合多线程
 */
//java.util.ConcurrentModificationException 并发修改错误
public class ListTest {
    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();  //不安全集合，多线程情况下会发生错误
        /*
        解决方案:
            1.List<String> list = new Vector<>();//使用线程安全集合
            2.List<String> list = Collections.synchronizedList(new ArrayList<>());//集合自带的安全类
            3.List<String> list = new CopyOnWriteArrayList<>(); //JUC包中的集合类
         */
        //CopyOnWriteArrayList 对比 Vector  前者使用lock锁 后者使用synchronized 前者效率高！
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
