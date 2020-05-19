package com.javaExtendsDemo.demo1;

import java.util.Objects;

public class Animal {
    /*公共的属性*/
    String breed;//品种
    int age;
    boolean sex;//true 代表公 false 代表母

    public Animal() {
    }

    /*公共的方法*/
    public void eat() {
        System.out.println(this.breed + " 父类在吃......");
    }

    public void sleep() {
        System.out.println(this.breed + " 父类在睡觉......");
    }

    public Animal(String breed, int age, boolean sex) {
        this.breed = breed;
        this.age = age;
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "breed='" + breed + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                sex == animal.sex &&
                breed.equals(animal.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, age, sex);
    }
}
