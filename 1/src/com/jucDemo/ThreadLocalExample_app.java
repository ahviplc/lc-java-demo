package com.jucDemo;

import com.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * JavaGuide/JavaConcurrencyAdvancedCommonInterviewQuestions.md at master · Snailclimb/JavaGuide
 * https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/Multithread/JavaConcurrencyAdvancedCommonInterviewQuestions.md#3-threadlocal
 */
public class ThreadLocalExample_app implements Runnable {

    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Override
    public void run() {
        System.out.println(DateUtil.currentStr() + " Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println(DateUtil.currentStr() + " Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample_app obj = new ThreadLocalExample_app();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

//    2020-05-19 10:57:20 Thread Name= 0 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:21 Thread Name= 1 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:21 Thread Name= 1 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:21 Thread Name= 0 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:21 Thread Name= 2 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:22 Thread Name= 2 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:22 Thread Name= 3 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:23 Thread Name= 4 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:23 Thread Name= 3 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:23 Thread Name= 5 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:24 Thread Name= 4 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:24 Thread Name= 6 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:24 Thread Name= 7 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:24 Thread Name= 8 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:24 Thread Name= 5 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:24 Thread Name= 6 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:24 Thread Name= 9 default Formatter = yyyyMMdd HHmm
//    2020-05-19 10:57:24 Thread Name= 7 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:24 Thread Name= 8 formatter = yy-M-d ah:mm
//    2020-05-19 10:57:25 Thread Name= 9 formatter = yy-M-d ah:mm

//从输出中可以看出，Thread-0已经改变了formatter的值，但仍然是thread-2默认格式化程序与初始化值相同，其他线程也一样。
//
//上面有一段代码用到了创建 ThreadLocal 变量的那段代码用到了 Java8 的知识，它等于下面这段代码，如果你写了下面这段代码的话，
//IDEA会提示你转换为Java8的格式(IDEA真的不错！)。因为ThreadLocal类在Java 8中扩展，使用一个新的方法withInitial()，将Supplier功能接口作为参数。

//    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
//        @Override
//        protected SimpleDateFormat initialValue()
//        {
//            return new SimpleDateFormat("yyyyMMdd HHmm");
//        }
//    };

}
