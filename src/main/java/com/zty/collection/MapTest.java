package com.zty.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version V1.0
 * @ClassName: com.zty.collection.MapTest.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-24 20:34
 * @Description: map多线程测试
 */
//java.util.ConcurrentModificationException
public class MapTest {
    public static void main(String[] args) {
        //默认等于   new HashMap<>(16,0.75) //初始大小 、 负载因子
        // Map<String,String> map = new HashMap<>(); //java.util.ConcurrentModificationException
        /*
        解决方案：
            1.Map<String,String> map = new Hashtable<>();
            2.Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
            3.Map<String,String> map = new ConcurrentHashMap<>();
         */
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,5),Thread.currentThread().getName());
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}