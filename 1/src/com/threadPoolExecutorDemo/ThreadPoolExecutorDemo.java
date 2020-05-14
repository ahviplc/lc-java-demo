package com.threadPoolExecutorDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 编写测试程序，我们这里以阿里巴巴推荐的使用 ThreadPoolExecutor 构造函数自定义参数的方式来创建线程池。
 */
public class ThreadPoolExecutorDemo {

//    下面的代码指定了：
//    corePoolSize: 核心线程数为 5。
//    maximumPoolSize ：最大线程数 10
//    keepAliveTime : 等待时间为 1L。
//    unit: 等待时间的单位为 TimeUnit.SECONDS。
//    workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;
//    handler:饱和策略为 CallerRunsPolicy。

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i + "LC");
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}

//执行结果

//        pool-1-thread-5 Start. Time = Thu May 14 15:12:45 CST 2020 开始时间: 2020-05-14 15:12:45 数据: 4LC
//        pool-1-thread-2 Start. Time = Thu May 14 15:12:45 CST 2020 开始时间: 2020-05-14 15:12:45 数据: 1LC
//        pool-1-thread-3 Start. Time = Thu May 14 15:12:45 CST 2020 开始时间: 2020-05-14 15:12:45 数据: 2LC
//        pool-1-thread-4 Start. Time = Thu May 14 15:12:45 CST 2020 开始时间: 2020-05-14 15:12:45 数据: 3LC
//        pool-1-thread-1 Start. Time = Thu May 14 15:12:45 CST 2020 开始时间: 2020-05-14 15:12:45 数据: 0LC
//        pool-1-thread-5 End. Time = Thu May 14 15:12:50 CST 2020 结束时间: 2020-05-14 15:12:50 数据: 4LC
//        pool-1-thread-5 Start. Time = Thu May 14 15:12:50 CST 2020 开始时间: 2020-05-14 15:12:50 数据: 5LC
//        pool-1-thread-2 End. Time = Thu May 14 15:12:50 CST 2020 结束时间: 2020-05-14 15:12:50 数据: 1LC
//        pool-1-thread-3 End. Time = Thu May 14 15:12:50 CST 2020 结束时间: 2020-05-14 15:12:50 数据: 2LC
//        pool-1-thread-2 Start. Time = Thu May 14 15:12:50 CST 2020 开始时间: 2020-05-14 15:12:50 数据: 6LC
//        pool-1-thread-3 Start. Time = Thu May 14 15:12:50 CST 2020 开始时间: 2020-05-14 15:12:50 数据: 7LC
//        pool-1-thread-4 End. Time = Thu May 14 15:12:50 CST 2020 结束时间: 2020-05-14 15:12:50 数据: 3LC
//        pool-1-thread-1 End. Time = Thu May 14 15:12:50 CST 2020 结束时间: 2020-05-14 15:12:50 数据: 0LC
//        pool-1-thread-1 Start. Time = Thu May 14 15:12:50 CST 2020 开始时间: 2020-05-14 15:12:50 数据: 8LC
//        pool-1-thread-4 Start. Time = Thu May 14 15:12:50 CST 2020 开始时间: 2020-05-14 15:12:50 数据: 9LC
//        pool-1-thread-5 End. Time = Thu May 14 15:12:55 CST 2020 结束时间: 2020-05-14 15:12:55 数据: 5LC
//        pool-1-thread-2 End. Time = Thu May 14 15:12:55 CST 2020 结束时间: 2020-05-14 15:12:55 数据: 6LC
//        pool-1-thread-3 End. Time = Thu May 14 15:12:55 CST 2020 结束时间: 2020-05-14 15:12:55 数据: 7LC
//        pool-1-thread-1 End. Time = Thu May 14 15:12:55 CST 2020 结束时间: 2020-05-14 15:12:55 数据: 8LC
//        pool-1-thread-4 End. Time = Thu May 14 15:12:55 CST 2020 结束时间: 2020-05-14 15:12:55 数据: 9LC
//        Finished all threads

//分析:
//我们通过代码输出结果可以看出：线程池每次会同时执行 5 个任务，这 5 个任务执行完之后，剩余的 5 个任务才会被执行
//也就是
//我们在代码中模拟了 10 个任务，我们配置的核心线程数为 5 、等待队列容量为 100 ，所以每次只可能存在 5 个任务同时执行，剩下的 5 个任务会被放到等待队列中去。当前的 5 个任务之行完成后，才会之行剩下的 5 个任务。