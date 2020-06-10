package com.myToolsByLCDemo;

import com.lc.utils.DateUtils;
import com.lc.utils.StringUtils;

public class myToolsTest {
    public static void main(String[] args) {
        System.out.println(StringUtils.toUpper("lc"));
        System.out.println(StringUtils.toLower("LC"));
        System.out.println(DateUtils.getTimeStamp());
    }
}