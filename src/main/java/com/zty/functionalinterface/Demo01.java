package com.zty.functionalinterface;

import java.util.function.Function;

/**
 * @version V1.0
 * @ClassName: com.zty.functionalinterface.Demo01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 16:41
 * @Description: Function  函数型接口  有一个输入，有一个输出
 *               只要是函数型接口就可以用lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {

        //匿名内部类实现函数型接口
        //Function<String, String> function = new Function<String, String>() {
        //    @Override
        //    public String apply(String o) {
        //        return o;
        //    }
        //};

        //等同于上诉代码
        Function<String, String> function = (str)->{return str;};

        System.out.println(function.apply("zty"));
    }
}
