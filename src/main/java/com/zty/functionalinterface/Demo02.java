package com.zty.functionalinterface;

import java.util.function.Predicate;

/**
 * @version V1.0
 * @ClassName: com.zty.functionalinterface.Demo02.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 16:50
 * @Description: Predicate 函数型接口  有一个输入参数， 返回值只能是 布尔值！
 */
public class Demo02 {
    public static void main(String[] args) {

        //判断字符串是否为空
        //Predicate<String> predicate = new Predicate<String>() {
        //    @Override
        //    public boolean test(String o) {
        //        return o.isEmpty();
        //    }
        //};

        //lambda表达式简化
        Predicate<String> predicate = (str)->{return str.isEmpty();};


        System.out.println(predicate.test("44"));
    }
}
