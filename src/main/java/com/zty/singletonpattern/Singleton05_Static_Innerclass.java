package com.zty.singletonpattern;

/**
 * @version V1.0
 * @ClassName: com.zty.singletonpattern.Singleton05_static_innerclass.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 15:02
 * @Description: 静态内部类实现单例模式
 */
public class Singleton05_Static_Innerclass {

    public static class InnerClass{
        private static final Singleton05_Static_Innerclass INSTANCE = new Singleton05_Static_Innerclass();
    }

    private Singleton05_Static_Innerclass(){

    }

    public static Singleton05_Static_Innerclass getInstance(){
        return InnerClass.INSTANCE;
    }
}
