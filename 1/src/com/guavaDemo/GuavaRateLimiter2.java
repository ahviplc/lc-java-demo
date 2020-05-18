package com.guavaDemo;

import com.google.common.util.concurrent.RateLimiter;
import com.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter是Guava的concurrent包下的一个用于限制访问频率的类
 * 原理：Guava RateLimiter基于令牌桶算法，我们只需要告诉RateLimiter系统限制的QPS是多少，那么RateLimiter将以这个速度往桶里面放入令牌，然后请求的时候，通过tryAcquire()方法向RateLimiter获取许可（令牌）。
 *
 * 使用RateLimiter完成简单的大流量限流，抢购秒杀限流 - 不死码农 - 博客园
 * https://www.cnblogs.com/leeego-123/p/11493335.html
 * <p>
 * 有很多个任务，但希望每秒不超过X个，可用此类
 *
 *  ————————————————
 * isShutDown：当调用shutdown()或shutdownNow()方法后返回为true。 
 * isTerminated：当调用shutdown()方法后，并且所有提交的任务完成后返回为true;
 * isTerminated：当调用shutdownNow()方法后，成功停止后返回为true;
 * 如果线程池任务正常完成，都为false
 * ————————————————
 * Java线程池，isShutDown、isTerminated的作用与区别_java_坚持，让梦想闪耀！-CSDN博客
 * https://blog.csdn.net/u010002184/article/details/79188403
 */
public class GuavaRateLimiter2 {

    public static void main(String[] args) {
        Double dRate = 0.5d;
        //0.5代表一秒最多少个
        RateLimiter rateLimiter = RateLimiter.create(dRate);//0.5代表一秒最多执行0.5个  那执行1个也就是2秒 2秒执行一次  0.5是2分之1 用1除以其 就是2 也就是2秒
        System.out.println("getRate() 速率为: " + rateLimiter.getRate() + " 也就是: " + 1 / dRate + " 秒执行1个");
        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new UserRequest(i));
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (Runnable runnable : tasks) {
            System.out.println(DateUtil.currentStr() + " 此线程名称: " + Thread.currentThread().getName() + " 等待时间：" + rateLimiter.acquire());
            threadPool.execute(runnable);
        }
        System.out.println("shutdown之前 isShutdown:" + threadPool.isShutdown());
        System.out.println("shutdown之前 isTerminated:" + threadPool.isTerminated());
        threadPool.shutdown();
        //threadPool.shutdownNow(); //立马shutdown 不建议使用 请小心使用
        System.out.println("shutdown之后 isShutdown:" + threadPool.isShutdown());
        System.out.println("shutdown之后 isTerminated:" + threadPool.isTerminated());
        try {
            while (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("线程池没有关闭");
                System.out.println("isTerminated:" + threadPool.isTerminated());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程池已经关闭");
        System.out.println("isTerminated:" + threadPool.isTerminated());
    }

    /**
     * 继承Runnable 重写run方法
     */
    private static class UserRequest implements Runnable {
        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(id);
        }
    }

}
