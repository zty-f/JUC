package com.zty.singletonpattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-13 15:20
 * @Description: 双重检测锁（DCL）+volatile下的懒汉单例模式  通过反射破坏单例模式以及解决办法
 */
public class Singleton04_DCL_Volatile_Lazy_Reflect {

    private static volatile Singleton04_DCL_Volatile_Lazy_Reflect instance;//避免指令重排序

    private static boolean FLAG = true;

    private Singleton04_DCL_Volatile_Lazy_Reflect(){ //构造器私有
        synchronized (Singleton04_DCL_Volatile_Lazy_Reflect.class){
            if (FLAG){
                FLAG = false;
            }else{
                throw new RuntimeException("请不要试图用反射来破坏单例模式！");
            }
        }
        System.out.println(Thread.currentThread().getName()+"正在初始化单例！");
    }

    public static Singleton04_DCL_Volatile_Lazy_Reflect getInstance(){
        // 双重检测锁，DCL + volatile 懒汉式
        if (instance==null){
            synchronized (Singleton04_DCL_Volatile_Lazy_Reflect.class){
                if(instance==null){
                    instance = new Singleton04_DCL_Volatile_Lazy_Reflect();//不是原子性操作
                }
            }
        }
        return instance;
    }


    //尽管上诉方法已经很完美，但是通过反射仍然可以破坏单例模式
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //Singleton04_DCL_Volatile_Lazy_Reflect instance1 = Singleton04_DCL_Volatile_Lazy_Reflect.getInstance();

        //通过反射获取类变量，访问私有变量等等
        Field flag = Singleton04_DCL_Volatile_Lazy_Reflect.class.getDeclaredField("FLAG");
        flag.setAccessible(true);

        //通过反射获取构造器，然后使得私有方法可以获取然后创建实例！
        Constructor<Singleton04_DCL_Volatile_Lazy_Reflect> declaredConstructor = Singleton04_DCL_Volatile_Lazy_Reflect.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true); //将私有方法可以获取

        Singleton04_DCL_Volatile_Lazy_Reflect instance1 = declaredConstructor.newInstance();

        flag.set(instance1,true);//通过反射修改私有变量值

        Singleton04_DCL_Volatile_Lazy_Reflect instance2 = declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance2==instance1);
        /*
            尽管这些方法已经很不错的生成单例模式，但是也会有反射机制带来的风险

            所以就有了可以避免反射的单例模式------>枚举单例模式
         */
    }
}
