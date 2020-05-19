package com.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式单例 都是不安全的(因为反射)
 * 道高一尺,魔高一丈
 */
public class LazyManSingle {

    private static boolean isFlag = false;

    //首先私有构造器
    private LazyManSingle() {
        synchronized (LazyManSingle.class) {
            if (isFlag == false) {
                isFlag = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
//            if (lazyManSingle != null) {
//                throw new RuntimeException("不要试图使用反射破坏异常");
//            }
        }
        System.out.println(Thread.currentThread().getName() + " 创建单例ok");
    }

    private static volatile LazyManSingle lazyManSingle;

    //双重检测锁模式的懒汉式单例 DCL懒汉式 + 原子性操作
    public static LazyManSingle getInstance() {
        //加锁
        if (lazyManSingle == null) {
            synchronized (LazyManSingle.class) {
                if (lazyManSingle == null) {
                    lazyManSingle = new LazyManSingle();//不是原子性操作
                    /**
                     * 1、分配内存空间
                     * 2.执行构造方法,初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 问题:
                     * 123 ok
                     *
                     * 132 A线程 ok
                     *     B线程 B线程时 lazyManSingle还没有完成构造
                     *
                     * 解决:
                     * 要使用volatile关键字 防止其 指令重排
                     */
                }
            }
        }
        return lazyManSingle;
    }
}

//class testMain {
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazyManSingle.getInstance();
//                LazyManSingle lazyManSingle = LazyManSingle.getInstance();
//                System.out.println(lazyManSingle);
//            }).start();
//        }
//    }
//}

//反射
//class testMain2 {
//    public static void main(String[] args) throws Exception {
//        LazyManSingle lazyManSingle = LazyManSingle.getInstance();
//        //首先拿到反射对象
//        Constructor<LazyManSingle> declaredConstructor = LazyManSingle.class.getDeclaredConstructor(null);
//        //无视私有构造器
//        declaredConstructor.setAccessible(true);
//        LazyManSingle lazyManSingle1 = declaredConstructor.newInstance();
//        //输出
//        System.out.println(lazyManSingle);
//        System.out.println(lazyManSingle1);
//    }
//}

//反射 两个对象都是使用反射创立 new出来
class testMain3 {
    public static void main(String[] args) throws Exception {
        //LazyManSingle lazyManSingle = LazyManSingle.getInstance();
        Field isFlag = LazyManSingle.class.getDeclaredField("isFlag");
        isFlag.setAccessible(true);

        //首先拿到反射对象
        Constructor<LazyManSingle> declaredConstructor = LazyManSingle.class.getDeclaredConstructor(null);
        //无视私有构造器
        declaredConstructor.setAccessible(true);
        LazyManSingle lazyManSingle = declaredConstructor.newInstance();

        //isFlag再改回来 再次破解
        isFlag.set(lazyManSingle, false);

        LazyManSingle lazyManSingle1 = declaredConstructor.newInstance();
        //输出
        System.out.println(lazyManSingle);
        System.out.println(lazyManSingle1);
    }
}
