package com.jucDemo.function;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };
        System.out.println(predicate.test("123"));

        Predicate<String> predicate2 = (str) -> { return str.isEmpty(); };
        System.out.println(predicate2.test(""));

        Predicate<String> predicate3 = str -> str.isEmpty();
        System.out.println(predicate3.test("456"));
    }
}
