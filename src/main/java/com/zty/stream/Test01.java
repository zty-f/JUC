package com.zty.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @version V1.0
 * @ClassName: com.zty.stream.Test01.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 19:42
 * @Description: stream流测试类
 */
/*
 * 题目要求:一分钟内完成此题，只能用一行代码实现!
 * *现在有5个用户!筛选;
 *1、ID 必须是偶数
 *2、年龄必须大于20岁
 *3、用户名转为大写字母
 *4、用户名字母倒着排序
 *5、只输出一个用户!
 */
public class Test01 {
    public static void main(String[] args) {
        User u1 = new User(1,"a",18);
        User u2 = new User(2,"b",19);
        User u3 = new User(3,"c",20);
        User u4 = new User(4,"d",21);
        User u5 = new User(5,"e",22);
        User u6 = new User(6,"f",26);
        User u7 = new User(8,"g",25);
        //集合进行存储
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5,u6,u7);

        //运算交给流Stream
        list.stream()
                .filter(user -> {return user.getId()%2==0;})//普通写法
                .filter(user -> user.getAge()>20)  //一行代码可以省略 return 和 {}
                .map(user -> {
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .sorted((a,b)->b.getName().compareTo(a.getName()))  //倒序排列
                .limit(1)
                .forEach(System.out::println);  //stream流输出格式  lambda简写形式 等同于 .forEach(user -> System.out.println(user))

    }
}
