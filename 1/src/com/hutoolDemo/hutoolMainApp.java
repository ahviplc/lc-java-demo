package com.hutoolDemo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.emoji.EmojiUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;
import cn.hutool.script.ScriptUtil;
import cn.hutool.system.SystemUtil;
import com.jucDemo.stream.User2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import static cn.hutool.core.util.NumberUtil.round;

public class hutoolMainApp {
    // 定义分割线
    public static String lines = "==============================================";

    public static void main(String[] args) throws NoSuchMethodException {
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        System.out.println(DateUtil.now());

        ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
        // 一月
        // System.out.println(date.getChineseMonth());
        Console.print(date.getChineseMonth());
        // 正月
        date.getChineseMonthName();
        Console.log(date.getChineseMonthName());
        // 初一
        date.getChineseDay();
        // 庚子
        date.getCyclical();
        // 鼠
        date.getChineseZodiac();
        // 春节
        date.getFestivals();
        // 庚子鼠年 正月初一
        date.toString();
        Console.log(lines);

        File[] ls = FileUtil.ls("D:");
        System.out.println(ls.length);
        for (File file : ls) {
            System.out.println(file.getName());
        }
        Console.log(lines);

        Method[] methods = ReflectUtil.getMethods(User2.class);
        for (Method method : methods) {
            Console.log(method);
        }
        Console.log(lines);

        User2 testClassUser = new User2();
        ReflectUtil.invoke(testClassUser, "setAge", 10);
        System.out.println(testClassUser.getAge());

        User2 user2 = ReflectUtil.newInstance(User2.class);
        System.out.println(user2.getAge());
        Console.log(lines);

        Console.log(RuntimeUtil.execForStr("ipconfig"));
        Console.log(lines);

        double te1 = 123456.123456;
        double te2 = 123456.128456;
        Console.log(round(te1, 4)); //结果:123456.1235
        Console.log(round(te2, 4)); //结果:123456.1285
        Console.log(round(te1, 2)); //结果:123456.12
        Console.log(round(te2, 2)); //结果:123456.13
        Console.log(lines);

        //参数1为终端ID
        //参数2为数据中心ID
        //雪花算法
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        Console.log(id);
        Console.log(lines);

//生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();

//生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        Console.log(lines);

        Dict dict = Dict.create()
                .set("key1", 1)//int
                .set("key2", 1000L)//long
                .set("key3", DateTime.now());//Date
        System.out.println(dict.get("key3"));
        Console.log(lines);

        boolean isEmail = Validator.isEmail("loolly@gmail.com");
        Console.log(lines);

        System.out.println(StrUtil.format("this is {} for {}", "a", "b"));
        Console.log(lines);

//        自动根据用户引入的分词库的jar来自动选择使用的引擎
//        TokenizerEngine engine = new HanLPEngine();
//        //解析文本
//        String text = "这两个方法的区别在于返回值";
//        Result result = engine.parse(text);
//        //输出：这 两个 方法 的 区别 在于 返回 值
//        String resultStr = CollUtil.join((Iterator<Word>) result, " ");
//        Console.log(resultStr);

        ScriptUtil.eval("print('Script test!');");

        System.out.println(SystemUtil.getRuntimeInfo());
        System.out.println(SystemUtil.getOsInfo());
        Console.log(lines);

        // CollUtil
        String[] col = new String[]{"a", "b", "c", "d", "e"};
        List<String> colList = CollUtil.newArrayList(col);
        String str = CollUtil.join(colList, "#");
        Console.log(str); // a#b#c#d#e
        // 判断指定集合是否包含指定值
        System.out.println(CollUtil.contains(colList, "a2")); // true
        System.out.println(CollUtil.contains(colList, "a2")); // false


//        // 高并发测试-ConcurrencyTester
//        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
//            // 测试的逻辑内容
//            long delay = RandomUtil.randomLong(100, 1000);
//            ThreadUtil.sleep(delay);
//            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
//        });
//
//        // 获取总的执行时间，单位毫秒
//        Console.log(tester.getInterval()); // 1004 毫秒

//        // EmojiUtil 需要 https://github.com/vdurmont/emoji-java
//        String alias = EmojiUtil.toAlias("😄");//:smile:
//        Console.log(alias);
//
//        String emoji = EmojiUtil.toUnicode(":smile:");//😄
//        Console.log(emoji);
    }
}
