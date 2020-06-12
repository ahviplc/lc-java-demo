package com.bitSetDemo;

import java.util.*;

/**
 * 有1百个随机数，随机数的范围在1到1千之间。现在要求写出一种算法，将1到1千之间没有在随机数中的数求出来？
 */
public class bitSetTest {
    public static void main(String[] args) {

        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomResult = random.nextInt(1000);
            list.add(randomResult);
        }
        System.out.println("size:" + list.size());
        System.out.println("产生的随机数有");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        BitSet bitSet = new BitSet(1000);

        Set set = new HashSet();

        for (Integer integer : list) {
            bitSet.set(integer);

            if (set.contains(integer)) {
                System.out.println("list中重复数据: " + integer);
            } else {
                set.add(integer);
            }
        }

        System.out.println("set size: " + set.size());

//        for (int i = 0; i < 100; i++) {
//            bitSet.set(list.get(i));
//        }

        System.out.println("0~1千不在上述随机数中有 " + bitSet.cardinality() + " 个");
        for (int i = 0; i < 1000; i++) {
            if (!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
