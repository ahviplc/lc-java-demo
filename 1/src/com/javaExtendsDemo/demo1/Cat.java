package com.javaExtendsDemo.demo1;

public class Cat extends Animal {
    //表示猫拥有的属性
    //String breed;
    //int age;
    //boolean sex;

    String furColor;

    //和父类共有的属性
    public Cat(String breed, int age, boolean sex) {
        super(breed, age, sex);
    }

    public Cat() {
    }

    /*表示猫拥有的方法*/
    //public void eat(){}
    //public void sleep(){}
    public void play() {
        System.out.println(this.breed + " 猫在玩......");
    }

}
