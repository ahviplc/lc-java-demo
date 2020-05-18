package com.jucDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程安全的CopyOnWriteArrayList介绍 - 曹明 - 博客园
 * https://www.cnblogs.com/think90/p/11426419.html
 */
public class copyOnWriteArrayList_app {

    public static void main(String[] args) {
        //1、初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer[]{1, 2});
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);


        //2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new copyOnWriteArrayList_class.ReadThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.WriteThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.WriteThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.WriteThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.ReadThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.WriteThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.ReadThread(copyList));
        executorService.execute(new copyOnWriteArrayList_class.WriteThread(copyList));

        System.out.println("copyList size:" + copyList.size());


//        copyList size:2
//        ReadThread:1 pool-1-thread-1 11
//        ReadThread:2 pool-1-thread-1 11
//        now this list size: 3
//        now this list size: 4
//        now this list size: 5
//        ReadThread:1 pool-1-thread-5 15
//        ReadThread:2 pool-1-thread-5 15
//        ReadThread:9 pool-1-thread-5 15
//        ReadThread:9 pool-1-thread-5 15
//        ReadThread:9 pool-1-thread-5 15
//        now this list size: 6
//        ReadThread:1 pool-1-thread-7 17
//        now this list size: 7
//        ReadThread:2 pool-1-thread-7 17
//        ReadThread:9 pool-1-thread-7 17
//        ReadThread:9 pool-1-thread-7 17
//        ReadThread:9 pool-1-thread-7 17
//        ReadThread:9 pool-1-thread-7 17
    }

}
