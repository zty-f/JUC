package com.zty.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @version V1.0
 * @ClassName: com.zty.forkjoin.Test.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 20:43
 * @Description: ForkJoin测试类
 */

//比较三种方式求和运算
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();//8264
        test2();//4924
        test3();//251
    }

    //暴力解法
    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 0L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+":::运行时长："+(end-start));
    }

    //ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+":::运行时长："+(end-start));
    }

    //Stream并行流
    public static void test3(){
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L)
                .parallel()
                .reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+":::运行时长："+(end-start));
    }
}
