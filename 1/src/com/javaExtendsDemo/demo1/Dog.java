package com.javaExtendsDemo.demo1;

public class Dog extends Animal {
    //表示狗拥有的属性
    //String breed;//狗的品种
    //int age;
    //boolean sex;

    String furColor;

    //和父类共有的属性
    public Dog(String breed, int age, boolean sex) {
        super(breed, age, sex);
    }

    public Dog() {
    }

    /*表示狗拥有的方法*/
    //public void sleep(){}


    /**
     * 重写父类 eat()方法
     */
    @Override
    public void eat() {
        //super.eat();
        System.out.println(this.breed + " 狗在吃......");
    }

    public void run() {
        System.out.println(this.breed + " 狗在跑......");
    }

    public void swim() {
        System.out.println(this.breed + " 狗在游泳......");
    }

    public void shout() {
        System.out.println(this.breed + " 狗在犬叫......");
    }

}
