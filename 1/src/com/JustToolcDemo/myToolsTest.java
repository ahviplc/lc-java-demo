package com.JustToolcDemo;

import com.lc.core.StrFormatter;
import com.lc.lang.UConsole;
import com.lc.utils.UDateUtil;
import com.lc.utils.UNumberUtil;
import com.lc.utils.UStringUtil;

import java.util.Date;

public class myToolsTest {
    public static void main(String[] args) {
        System.out.println(UStringUtil.toUpper("lc"));
        System.out.println(UStringUtil.toLower("LC"));
        UConsole.log(UStringUtil.padPre("1", 8, "0")); // 00000001
        UConsole.log(UStringUtil.padAfter("1", 8, "0")); // 10000000
        UConsole.log(UStringUtil.padEnd("1", 8, '0')); // 10000000
        UConsole.log(UStringUtil.padEnd("1", 8, "0".charAt(0))); // 10000000

        System.out.println(UDateUtil.getTimeStamp());
        System.out.println(UDateUtil.getNowDate_EN());
        System.out.println(UDateUtil.currentStr());
        System.out.println(UDateUtil.getNowTime_EN());
        System.out.println(UDateUtil.getNowTime_EN_yMdH());
        System.out.println(UDateUtil.getNowTime_CN_yMdH());
        System.out.println(UDateUtil.getNowTime_EN_yMdHm());
        System.out.println(UDateUtil.getNowTime_EN_yyyyMMddHHmm());
        System.out.println(UDateUtil.getNowTime_CN_yMdHm());
        System.out.println(UDateUtil.getNowTime_CN_HHmmss());

        UConsole.log(1);
        UConsole.log("5213344");
        UConsole.log(new Date());
        UConsole.log(UDateUtil.currentStr());
        UConsole.log("我是{},今年{},忍!", "LC", 18);
        UConsole.print("print");
        UConsole.print("我是{}", "print");
        UConsole.log();
        UConsole.print(StrFormatter.format("我是{},今年{},忍!", "LC2", 19));
        UConsole.log();
        UConsole.error("error");
        UConsole.error("我是{}", "error");

        UConsole.log(UNumberUtil.add(1, 1)); // 2.0
        UConsole.log(UNumberUtil.add(2.22f, 1.11f)); // 3.33
        UConsole.log(UNumberUtil.sub(522.3344, 1)); // 521.3344
        UConsole.log(UNumberUtil.mul(100, 5.21)); // 521.0
        UConsole.log(UNumberUtil.div(100, 3)); // 33.3333333333
    }
}