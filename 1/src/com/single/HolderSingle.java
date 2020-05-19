package com.single;

//静态内部类单例 都是不安全的(因为反射)
public class HolderSingle {
    //首先私有构造器
    private HolderSingle() {

    }

    public static HolderSingle getInstance() {
        return InnerClass.HOLDER_SINGLE;
    }

    public static class InnerClass {
        private static final HolderSingle HOLDER_SINGLE = new HolderSingle();
    }
}
