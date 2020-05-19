package com.javaExtendsDemo.demo1;

public class Bird extends Animal {
    //表示鸟拥有的属性
    //String breed;
    //int age;
    //boolean sex;

    //和父类共有的属性
    public Bird(String breed, int age, boolean sex) {
        super(breed, age, sex);
    }

    public Bird() {
    }

    /*表示狗拥有的方法*/
    //public void eat(){}
    //public void sleep(){}

    public void fly() {
        System.out.println(this.breed + " 鸟在飞......");
    }
}
