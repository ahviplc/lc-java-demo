package com.single;

import java.lang.reflect.Constructor;

// enum 是什么？ 本身也是一个类
// 枚举单例 是最完美的实现
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class TestMain {
    public static void main(String[] args) throws Exception {
        EnumSingle enumSingle = EnumSingle.INSTANCE;
        System.out.println(enumSingle);
        //首先拿到反射对象
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        //无视私有构造器
        declaredConstructor.setAccessible(true);
        EnumSingle enumSingle1 = declaredConstructor.newInstance();
        System.out.println(enumSingle1);
    }

    /**
     * Exception in thread "main" java.lang.NoSuchMethodException: com.single.EnumSingle.<init>()
     * 	at java.base/java.lang.Class.getConstructor0(Class.java:3349)
     * 	at java.base/java.lang.Class.getDeclaredConstructor(Class.java:2553)
     * 	at com.single.TestMain.main(EnumSingle.java:19)
     *
     *  上面的输出不对.
     *
     * 使用反编译工具jad.exe 反编译
     * 命令:
     *  .\jad.exe -sjava .\EnumSingle.class
     *
     * 输出提示:
     * Parsing .\EnumSingle.class... Generating EnumSingle.java
     *
     * 其EnumSingle.java的内容:
     *
     * // Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
     * // Jad home page: http://www.kpdus.com/jad.html
     * // Decompiler options: packimports(3)
     * // Source File Name:   EnumSingle.java
     *
     * package com.single;
     *
     *
     * public final class EnumSingle extends Enum
     * {
     *
     *     public static EnumSingle[] values()
     *     {
     *         return (EnumSingle[])$VALUES.clone();
     *     }
     *
     *     public static EnumSingle valueOf(String s)
     *     {
     *         return (EnumSingle)Enum.valueOf(com/single/EnumSingle, s);
     *     }
     *
     *     private EnumSingle(String s, int i)
     *     {
     *         super(s, i);
     *     }
     *
     *     public EnumSingle getInstance()
     *     {
     *         return INSTANCE;
     *     }
     *
     *     public static final EnumSingle INSTANCE;
     *     private static final EnumSingle $VALUES[];
     *
     *     static
     *     {
     *         INSTANCE = new EnumSingle("INSTANCE", 0);
     *         $VALUES = (new EnumSingle[] {
     *             INSTANCE
     *         });
     *     }
     * }
     *
     * com/single/EnumSingle.java:19 改为
     * Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
     *
     * 再运行:
     *
     * Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
     * 	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:484)
     * 	at com.single.TestMain.main(EnumSingle.java:22)
     *
     * 输出这样 就是对的 不能试图使用反射破坏异常
     */
}
