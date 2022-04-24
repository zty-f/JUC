package com.zty.producerAndconsumer;

/**
 * @version V1.0
 * @ClassName: com.zty.producerAndconsumer.producer.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-23 21:03
 * @Description: 原始生产者消费者问题
 */
public class App {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
// 等待、业务、通知
class Data{
    private int number = 0;

    public synchronized void produce() throws InterruptedException {
        //if(number!=0){  //使用if进行资源互斥会造成虚假唤醒问题
        //    //等待
        //    this.wait();
        //}
        while(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"===>"+number);
        //通知其他线程，我生产+1完毕
        this.notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"===>"+number);
        this.notifyAll();
    }

}
