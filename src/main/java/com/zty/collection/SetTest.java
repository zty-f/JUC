package com.zty.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version V1.0
 * @ClassName: com.zty.collection.SetTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-24 20:24
 * @Description: set多线程测试
 */
public class SetTest {
    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();  //java.util.ConcurrentModificationException
        /*
        解决方案:
            1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
            2.Set<String> set = new CopyOnWriteArraySet<>();
         */
        //CopyOnWriteArrayList 对比 Vector  前者使用lock锁 后者使用synchronized 前者效率高！
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
