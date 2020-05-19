package com.single;

/**
 * 恶汉式单例 都是不安全的(因为反射)
 */
public class HungrySingle {

//    可能浪费空间 所以出现懒汉式单例
//    private byte[] data1 = new byte[1024*1024];
//    private byte[] data2 = new byte[1024*1024];
//    private byte[] data3 = new byte[1024*1024];

    //首先私有构造器
    private HungrySingle() {

    }

    private final static HungrySingle hungrySingle = new HungrySingle();

    public static HungrySingle getInstance() {
        return hungrySingle;
    }
}
