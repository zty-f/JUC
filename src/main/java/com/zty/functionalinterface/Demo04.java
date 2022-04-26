package com.zty.functionalinterface;

import java.util.function.Supplier;

/**
 * @version V1.0
 * @ClassName: com.zty.functionalinterface.Demo04.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 17:04
 * @Description:  Supplier 供给型接口  没有输入参数 只有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        //Supplier<String> supplier = new Supplier<String>() {
        //    @Override
        //    public String get() {
        //        return "商品";
        //    }
        //};

        //lambda表达式优化
        Supplier<String> supplier = ()->{return "1024";};

        System.out.println(supplier.get());
    }
}
