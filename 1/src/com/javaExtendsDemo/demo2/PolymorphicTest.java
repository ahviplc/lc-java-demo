package com.javaExtendsDemo.demo2;

/**
 * Java 继承详解 | 菜鸟教程
 * https://www.runoob.com/w3cnote/java-extends.html
 * <p>
 * 多态
 * 概念：
 * <p>
 * 对象在不同时刻表现出来的不同状态。
 * <p>
 * 多态的前提：
 * <p>
 * 要有继承或者实现关系。
 * 要有方法的重写。
 * 要有父类引用指向子类对象。
 * 程序中的体现：
 * 父类或者接口的引用指向或者接收自己的子类对象。
 * <p>
 * 好处和作用：
 * 多态的存在提高了程序的扩展性和后期可维护性。
 * <p>
 * 弊端:
 * 父类调用的时候只能调用父类里的方法，不能调用子类的特有方法，因为你并不清楚将来会有什么样的子类继承你。
 * <p>
 * 多态的成员特点：
 * <p>
 * 成员变量：编译时期：看引用型变量所属的类中是否有所调用的变量；
 * 运行时期：也是看引用型变量所属的类是否有调用的变量。
 * 成员变量无论编译还是运行都看引用型变量所属的类，简单记成员变量，编译和运行都看等号左边。
 * 成员方法：编译时期：要查看引用变量所属的类中是否有所调用的成员；
 * 运行时期：要查看对象所属的类中是否有所调用的成员。如果父子出现同名的方法,会运行子类中的方法,因为方法有覆盖的特性。
 * 编译看左边运行看右边。
 * 静态方法:编译时期：看的引用型变量所属的类中是否有所调用的变量；
 * 运行时期：也是看引用型变量所属的类是否有调用的变量。
 * 编译和运行都看等号左边。
 * 一定不能够将父类的对象转换成子类类型！
 * <p>
 * 父类的引用指向子类对象，该引用可以被提升，也可以被强制转换。
 * <p>
 * 多态自始至终都是子类对象在变化！
 */

//多态向下转型和向上转型的例子，多态转型解决了多态中父类引用不能使用子类特有成员的弊端。
public class PolymorphicTest {
    public static void main(String[] args) {
        Phone p1 = new Nokia();        //向上转型，类型提升
        Nokia no = (Nokia) p1;          //向下转型，强制将父类的引用转换成子类类型，不能将Nokia类型转成Moto或Nexus类型
        no.print();                    //输出结果为Phone---null---0，因为继承了父类的方法

        Phone p2 = new Moto();
        Moto m = (Moto) p2;
        m.print();                    //输出结果为Moto---yellow---1599，方法重写，子类方法覆盖父类方法

        Phone p3 = new Nexus();
        Nexus ne = (Nexus) p3;
        ne.print();
    }

//    Phone---null---0
//    Moto---yellow---1599
//    Nexus---black---1999
}

class Phone {
    String color;
    int price;

    public void print() {
        System.out.println("Phone---" + color + "---" + price);
    }
}

class Nokia extends Phone {
    String color = "red";
    int price = 1009;

    //public void print(){
    //    System.out.println("Nokia---" + color + "---" + price);
    //}
}

class Moto extends Phone {
    String color = "yellow";
    int price = 1599;

    public void print() {
        System.out.println("Moto---" + color + "---" + price);
    }
}

class Nexus extends Phone {
    String color = "black";
    int price = 1999;

    public void print() {
        System.out.println("Nexus---" + color + "---" + price);
    }
}
