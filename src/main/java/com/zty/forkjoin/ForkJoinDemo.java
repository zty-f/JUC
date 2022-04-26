package com.zty.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @version V1.0
 * @ClassName: com.zty.forkjoin.ForkJoinDemo.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 20:12
 * @Description: Jdk1.7 引入ForkJoin，用于并行执行任务！提高效率！大数据
 *   计算任务类
 */
//ForkJoin特点: 工作窃取(当前线程任务完成后主动去窃取其他没有完成任务的线程的任务来替他执行任务，提高工作效率)
/*
    求和计算的任务！
    1.暴力
    2.ForkJoin
        如何使用ForkJoin
            通过forkjoinPool来执行
            计算任务  forkjoinPool.execute(ForkJoinTask<?> task)
            计算类要继承 RecursiveTask
    3.Stream并行流
 */
public class ForkJoinDemo extends RecursiveTask<Long> { //类似于递归任务执行

    private Long start;

    private Long end;

    //临界值
    private Long point = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    //计算方法   ForkJoin
    @Override
    protected Long compute() {
        if (end-start>point){
            //分支合并计算
            long mid = (start+end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork(); //拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(mid+1, end);
            task2.fork();
            return task1.join()+task2.join();
        }else{
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
