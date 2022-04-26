package com.zty.functionalinterface;

import java.util.function.Consumer;

/**
 * @version V1.0
 * @ClassName: com.zty.functionalinterface.Demo03.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 16:59
 * @Description: Consumer 函数型接口（消费型接口）  只有输入 没有返回值
 */
public class Demo03 {
    public static void main(String[] args) {

        //Consumer<String> consumer = new Consumer<String>() {
        //    @Override
        //    public void accept(String s) {
        //        System.out.println("消费了："+s);
        //    }
        //};

        //lambda表达式优化
        Consumer<String> consumer = (str)->{System.out.println(str);} ;

        consumer.accept("商品");
    }
}
