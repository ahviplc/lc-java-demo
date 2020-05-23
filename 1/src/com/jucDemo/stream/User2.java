package com.jucDemo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 有参，无参构造，get、set、toString方法！
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 {
    private int id;
    private String name;
    private int age;
}
