package com.zty.singletonpattern;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-05-12 21:28
 * @Description: 双重检测锁（DCL）下的懒汉单例模式 存在指令重排序问题
 */
public class Singleton03_DCL_Lazy {

    private static Singleton03_DCL_Lazy instance;

    private Singleton03_DCL_Lazy(){ //构造器私有
        System.out.println(Thread.currentThread().getName()+"正在初始化单例！");
    }

    public static Singleton03_DCL_Lazy getInstance(){
        // 双重检测锁，DCL 懒汉式
        if (instance==null){
            synchronized (Singleton03_DCL_Lazy.class){
                if(instance==null){
                    instance = new Singleton03_DCL_Lazy();//不是原子性操作
                    /*
                        1.分配对象内存空间
                        2.执行构造方法，初始化对象
                        3.把这个对象指向分配的内存空间

                        在操作系统指令重排序中 2 3步骤可能交换！！！！

                        如果线程 A在 instance = new Singleton();发生重排序，
                        那么线程B在 第20行即第一个if (singleton == null)处就有可能判断出 singleton不为null，
                        进而拿到返回的 singleton 去做别的事进而导致报错。（比如数据错误异常等等）
                        所以就有了 ----双重检测锁+volatile+懒汉单例模式   （禁止指令重排序）
                     */
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton03_DCL_Lazy.getInstance();
            }).start();
        }
    }
}
