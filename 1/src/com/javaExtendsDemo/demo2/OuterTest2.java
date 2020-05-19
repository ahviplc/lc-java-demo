package com.javaExtendsDemo.demo2;

/**
 * Java内部类的定义、如何创建内部类、内部类的分类、内部类与外部类的关系_java_zhao_miao的博客-CSDN博客
 * https://blog.csdn.net/zhao_miao/article/details/83245816
 * <p>
 * 但是如果去掉内部类：
 */

class Outer2 {
    private String outStr = "Outer中的字符串";

    public String getOutStr() {
        return outStr;
    }

    public void fun() {  //2
        //this表示当前对象
        Inner2 in = new Inner2(this); //3
        in.print();                 //5
    }
}

class Inner2 {
    private String inStr = "Inner中的字符串";
    private Outer2 out2;

    //构造注入
    public Inner2(Outer2 out2)  //3
    {
        this.out2 = out2;       //4.为Inner中的out变量初始化
    }

    public void print() {    //6
        System.out.println(out2.getOutStr()); //7
    }
}

public class OuterTest2 {
    public static void main(String[] args) {
        {
            Outer2 out = new Outer2();  //1.
            out.fun(); //2.
        }
    }

    //Outer中的字符串
}
