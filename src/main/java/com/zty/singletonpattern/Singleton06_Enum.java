package com.zty.singletonpattern;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @version V1.0
 * @ClassName: com.zty.singletonpattern.Singleton06_Enum.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 15:39
 * @Description: 使用枚举实现单例模式
 */
public enum Singleton06_Enum { //枚举其实也是一个类继承枚举类
    INSTANCE;
    //默认这里有一个私有构造方法！！可以在编译生成的class文件查看 （有参数String,int)
    public Singleton06_Enum getInstance(){
        return INSTANCE;
    }

}
class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Singleton06_Enum instance1 = Singleton06_Enum.INSTANCE;
        Singleton06_Enum instance2 = Singleton06_Enum.INSTANCE;

        //使用反射创建实例
        Constructor<Singleton06_Enum> declaredConstructor = Singleton06_Enum.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        Singleton06_Enum instance3 = declaredConstructor.newInstance();

        //输出错误  单例模式不能使用反射破坏！！！！！
        /*
        Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
	    at java.lang.reflect.Constructor.newInstance(Constructor.java:417)
	    at com.zty.singletonpattern.Test.main(Singleton06_Enum.java:31)
         */
        System.out.println(instance3);
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
// 反编译下枚举类自动添加的私有构造方法！！！！！
/*
private EnumSingleton(String s, int i) {
    super(s, i);
}
 */