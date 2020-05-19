package com.javaExtendsDemo.demo2;

/**
 * Java 继承详解 | 菜鸟教程
 * https://www.runoob.com/w3cnote/java-extends.html
 * <p>
 * 接口（interface）
 * <p>
 * 接口是抽象方法和常量值的集合。从本质上讲，接口是一种特殊的抽象类，这种抽象类只包含常量和方法的定义，而没有变量和方法的实现。
 * <p>
 * 格式：interface 接口名{}
 * <p>
 * 接口的出现将"多继承"通过另一种形式体现出来，即"多实现"。
 * <p>
 * 实现（implements）
 * <p>
 * 格式：class 类名 implements 接口名 {}
 * <p>
 * 特点：
 * <p>
 * 接口不能被实例化。
 * 一个类如果实现了接口，要么是抽象类，要么实现接口中的所有方法。
 * 接口的成员特点：
 * <p>
 * 接口中的成员修饰符是固定的！
 * <p>
 * 成员常量：public static final，接口里定义的变量是全局常量，而且修饰符只能是这三个关键字，都可以省略，常量名要大写。
 * 成员方法：public abstract，接口里定义的方法都是抽象的，两个修饰符关键字可省略。
 * 推荐：永远手动给出修饰符。
 * 继承与实现的区别：
 * <p>
 * 类与类之间称为继承关系：因为该类无论是抽象的还是非抽象的，它的内部都可以定义非抽象方法，这个方法可以直接被子类使用，子类继承即可。只能单继承，可以多层继承。（(class)）
 * 类与接口之间是实现关系：因为接口中的方法都是抽象的，必须由子类实现才可以实例化。可以单实现，也可以多实现；还可以在继承一个类的同时实现多个接口。（(class) extends (class) implements (interface1,interface2…)）
 * 接口与接口之间是继承关系：一个接口可以继承另一个接口，并添加新的属性和抽象方法，并且接口可以多继承。（(interface) extends (interface1,interface2…)）
 * 抽象类和接口的区别：
 * <p>
 * 成员变量
 * <p>
 * 抽象类能有变量也可以有常量
 * 接口只能有常量
 * 成员方法
 * <p>
 * 抽象类可以有非抽象的方法,也可以有抽象的方法
 * 接口只能有抽象的方法
 * 构造方法
 * <p>
 * -抽象类有构造方法
 * -接口没有构造方法
 * <p>
 * 类与抽象类和接口的关系
 * <p>
 * 类与抽象类的关系是继承 extends
 * 类与接口的关系是实现 implements
 * 接口的思想特点：
 * <p>
 * 接口是对外暴露的规则；
 * 接口是程序的功能扩展；
 * 接口的出现降低耦合性；(实现了模块化开发,定义好规则,每个人实现自己的模块,大大提高了开发效率)
 * 接口可以用来多实现；
 * 多个无关的类可以实现同一个接口；
 * 一个类可以实现多个相互直接没有关系的接口；
 * 与继承关系类似，接口与实现类之间存在多态性。
 */

/*
    运动员和教练的案例（下图是思路分析）
    篮球运动员和教练
    乒乓球运动员和教练
    现在篮球运动员和教练要出国访问,需要学习英语
    请根据你所学的知识,分析出来哪些是类,哪些是抽象类,哪些是接口
    继承 多态 封装
*/
interface SpeakEnglish {
    public abstract void speak();
}

interface GoAboard {
    public abstract void aboard();
}

abstract class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    //吃饭
    public abstract void eat();

    //睡觉
    public void sleep() {
        System.out.println("Zzz... from 父类Person");
    }
}

//运动员
abstract class Player extends Person {
    public abstract void study();
}

//教练
abstract class Coach extends Person {
    public abstract void teach();
}

//篮球运动员
class BasketballPlayer extends Player implements SpeakEnglish, GoAboard {
    public void eat() {
        System.out.println(getAge() + "岁的" + getName() + "吃鸡腿");
    }

    public void study() {
        System.out.println(getAge() + "岁的" + getName() + "学扣篮");
    }

    public void speak() {
        System.out.println(getAge() + "岁的" + getName() + " Say English: Hello World");
    }

    public void aboard() {
        System.out.println(getAge() + "岁的" + getName() + " Go Aboard");
    }
}

//乒乓运动员
class PingPangPlayer extends Player {
    public void eat() {
        System.out.println(getAge() + "岁的" + getName() + "吃鸡蛋");
    }

    public void study() {
        System.out.println(getAge() + "岁的" + getName() + "学扣球");
    }
}

//篮球教练
class BasketballCoach extends Coach implements SpeakEnglish {
    public void eat() {
        System.out.println(getAge() + "岁的" + getName() + "啃鸡爪");
    }

    public void teach() {
        System.out.println(getAge() + "岁的" + getName() + "教扣篮");
    }

    public void speak() {
        System.out.println(getAge() + "岁的" + getName() + " Say English: Hello Java");
    }

    public void aboard() {
        System.out.println(getAge() + "岁的" + getName() + " Go Aboard");
    }
}

//乒乓球教练
class PingPangCoach extends Coach {
    public void eat() {
        System.out.println(getAge() + "岁的" + getName() + "吃鸡蛋皮");
    }

    public void teach() {
        System.out.println(getAge() + "岁的" + getName() + "教扣球");
    }
}

public class PlayerAndCoach {
    public static void main(String[] args) {
        //篮球运动员
        BasketballPlayer bp = new BasketballPlayer();
        bp.setName("篮球运动员LC");
        bp.setAge(18);
        bp.eat();
        bp.sleep();
        bp.study();
        bp.speak();
        bp.aboard();
        System.out.println("***********************");

        //篮球教练
        BasketballCoach bc = new BasketballCoach();
        bc.setName("篮球教练LC");
        bc.setAge(19);
        bc.eat();
        bc.sleep();
        bc.teach();
        bc.speak();
        bc.aboard();
        System.out.println("***********************");

        //乒乓球教练
        PingPangCoach ppc = new PingPangCoach();
        ppc.setName("乒乓球教练LC");
        ppc.setAge(20);
        ppc.eat();
        ppc.sleep();
        ppc.teach();
        System.out.println("***********************");

        //多态
        Person p = new BasketballPlayer();
        p.setName("Kobe Bryant");
        p.setAge(33);
        p.eat();
        p.sleep();
//        p.study();
//        p.speak();

        BasketballPlayer bp2 = (BasketballPlayer) p;
        bp2.study();
        bp2.speak();
        bp2.aboard();
        System.out.println("***********************");
    }

//    18岁的篮球运动员LC吃鸡腿
//    Zzz... from 父类Person
//    18岁的篮球运动员LC学扣篮
//    18岁的篮球运动员LC Say English: Hello World
//    18岁的篮球运动员LC Go Aboard
//    ***********************
//    19岁的篮球教练LC啃鸡爪
//    Zzz... from 父类Person
//    19岁的篮球教练LC教扣篮
//    19岁的篮球教练LC Say English: Hello Java
//    19岁的篮球教练LC Go Aboard
//    ***********************
//    20岁的乒乓球教练LC吃鸡蛋皮
//    Zzz... from 父类Person
//    20岁的乒乓球教练LC教扣球
//    ***********************
//    33岁的Kobe Bryant吃鸡腿
//    Zzz... from 父类Person
//    33岁的Kobe Bryant学扣篮
//    33岁的Kobe Bryant Say English: Hello World
//    33岁的Kobe Bryant Go Aboard
//    ***********************
}
