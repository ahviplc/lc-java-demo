package com.javaExtendsDemo.demo2;

/**
 * Java内部类的定义、如何创建内部类、内部类的分类、内部类与外部类的关系_java_zhao_miao的博客-CSDN博客
 * https://blog.csdn.net/zhao_miao/article/details/83245816
 * <p>
 * 使用内部类实现多继承：
 */

class A {
    private String name = "A类的私有域";

    public String getName() {
        return name;
    }
}

class B {
    private int age = 20;

    public int getAge() {
        return age;
    }
}

class Outter {
    private class InnerClassA extends A {
        public String name() {
            return super.getName();
        }
    }

    private class InnerClassB extends B {
        public int age() {
            return super.getAge();
        }
    }

    public String name() {
        return new InnerClassA().name();
    }

    public int age() {
        return new InnerClassB().age();
    }
}

public class OuterTest3 {
    public static void main(String[] args) {
        Outter outter = new Outter();
        System.out.println(outter.name());
        System.out.println(outter.age());
    }

//    A类的私有域
//    20

//    其他 了解 去看文章
//    Java内部类的定义、如何创建内部类、内部类的分类、内部类与外部类的关系_java_zhao_miao的博客-CSDN博客
//    https://blog.csdn.net/zhao_miao/article/details/83245816
}
