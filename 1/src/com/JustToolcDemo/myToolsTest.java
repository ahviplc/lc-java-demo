package com.JustToolcDemo;

import com.lc.utils.UDateUtils;
import com.lc.utils.UStringUtils;

public class myToolsTest {
    public static void main(String[] args) {
        System.out.println(UStringUtils.toUpper("lc"));
        System.out.println(UStringUtils.toLower("LC"));
        System.out.println(UDateUtils.getTimeStamp());
    }
}