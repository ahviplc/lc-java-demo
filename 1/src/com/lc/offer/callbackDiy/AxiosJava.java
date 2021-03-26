package com.lc.offer.callbackDiy;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.console;
import static java.lang.System.out;

/**
 * 在Java中如何编写回调函数，以及回调函数的简单应用 - osc_wo67p5c1的个人空间 - OSCHINA - 中文开源技术交流社区
 * https://my.oschina.net/u/4407434/blog/4043242
 * <p>
 * Http客户端工具类-HttpUtil
 * https://hutool.cn/docs/#/http/Http客户端工具类-HttpUtil
 * <p>
 * public static long downloadFile(String url, File destFile, StreamProgress streamProgress)
 * <p>
 * 就有回调函数,可参考
 */
public class AxiosJava {

    /**
     * 使用回调函数完成post请求操作
     *
     * @param url
     * @param body
     * @param myCallBack
     */
    private static void post(String url, Map body, MyCallBack myCallBack) {
        try {
            out.println("...AxiosJava post-开始进行post请求操作...");
            Console.log(StrUtil.format("请求url为 -> {}, 其body -> {}", url, body));
            String data2 = HttpUtil.post(url, body);
            Console.log("data2 -> {} ", data2);
            //链式构建请求
            String data = HttpRequest.post(url)
                    .header("Axiosjava-Token", "AxiosJava http")//头信息，多个头信息多次调用此方法即可
                    .form(body)//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            Thread.sleep(5000); //模拟耗时操作（如网络请求），操作正常返回"Success"，"Success"表示有效的数据
            // 下面一行 模拟请求错误
            // int i = 1 / 0;
            myCallBack.onSuccess("Success-请求的成功数据为->" + data);
        } catch (Exception ex) {
            Logger.getLogger(AxiosJava.class.getName()).log(Level.SEVERE, null, ex);
            // 操作出现问题返回"Failed"，"Failed"包含错误提示，如错误码等
            // return "Failed";
            myCallBack.onFailed("Failed->" + ex.toString());
        } finally {
            myCallBack.onFinally();
        }
    }

    /**
     * 使用回调函数完成get请求操作
     *
     * @param url
     * @param body
     * @param myCallBack
     */
    private static void get(String url, Map body, MyCallBack myCallBack) {
        try {
            out.println("...AxiosJava get-开始进行get请求操作...");
            Console.log(StrUtil.format("请求url为 -> {}, 其body -> {}", url, body));
            String data2 = HttpUtil.get(url, body);
            Console.log("data2 -> {} ", data2);
            //链式构建请求
            String data = HttpRequest.get(url)
                    .header("Axiosjava-Token", "AxiosJava http")//头信息，多个头信息多次调用此方法即可
                    .form(body)//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            Thread.sleep(5000); //模拟耗时操作（如网络请求），操作正常返回"Success"，"Success"表示有效的数据
            // 下面一行 模拟请求错误
            // int i = 1 / 0;
            myCallBack.onSuccess("Success-请求的成功数据为->" + data);
        } catch (Exception ex) {
            Logger.getLogger(AxiosJava.class.getName()).log(Level.SEVERE, null, ex);
            // 操作出现问题返回"Failed"，"Failed"包含错误提示，如错误码等
            // return "Failed";
            myCallBack.onFailed("Failed->" + ex.toString());
        } finally {
            myCallBack.onFinally();
        }
    }

    /**
     * post_run
     */
    public static void post_run() {
        Map mapBody = new HashMap();
        mapBody.put("name", "LC");
        mapBody.put("studentId", "1120131131");
        AxiosJava.post("http://httpbin.org/post", mapBody, new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Console.log("...调用成功,data数据为->{}", data);
            }

            @Override
            public void onFailed(String error) {
                Console.log("...调用失败,erro数据为->{}", error);
            }

            @Override
            public void onFinally() {
                Console.log("...调用结束了...");
            }
        });
    }

    /**
     * get_run
     */
    public static void get_run() {
        Map mapBody = new HashMap();
        mapBody.put("name", "LC");
        mapBody.put("studentId", "1120131131");
        AxiosJava.get("http://httpbin.org/get", mapBody, new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Console.log("...调用成功,data数据为->{}", data);
            }

            @Override
            public void onFailed(String error) {
                Console.log("...调用失败,erro数据为->{}", error);
            }

            @Override
            public void onFinally() {
                Console.log("...调用结束了...");
            }
        });
    }

    public static void main(String[] args) {
        // post_run();
        get_run();
    }
}

//    post_run();
//    ...AxiosJava post-开始进行post请求操作...
//    请求url为 -> http://httpbin.org/post, 其body -> {studentId=1120131131, name=LC}
//    data2 -> {
//    "args": {},
//    "data": "",
//    "files": {},
//    "form": {
//    "name": "LC",
//    "studentId": "1120131131"
//    },
//    "headers": {
//    "Accept": "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
//    "Accept-Encoding": "gzip, deflate",
//    "Accept-Language": "zh-CN,zh;q=0.8",
//    "Cache-Control": "no-cache",
//    "Content-Length": "28",
//    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
//    "Host": "httpbin.org",
//    "Pragma": "no-cache",
//    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool",
//    "X-Amzn-Trace-Id": "Root=1-605e0153-0ec0601a0d9acb7604568227"
//    },
//    "json": null,
//    "origin": "112.65.11.112",
//    "url": "http://httpbin.org/post"
//    }
//
//    ...调用成功,data数据为->Success-请求的成功数据为->{
//    "args": {},
//    "data": "",
//    "files": {},
//    "form": {
//    "name": "LC",
//    "studentId": "1120131131"
//    },
//    "headers": {
//    "Accept": "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
//    "Accept-Encoding": "gzip, deflate",
//    "Accept-Language": "zh-CN,zh;q=0.8",
//    "Axiosjava-Token": "AxiosJava http",
//    "Cache-Control": "no-cache",
//    "Content-Length": "28",
//    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
//    "Host": "httpbin.org",
//    "Pragma": "no-cache",
//    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool",
//    "X-Amzn-Trace-Id": "Root=1-605e0154-481aaadc48c6772356c46971"
//    },
//    "json": null,
//    "origin": "112.65.11.112",
//    "url": "http://httpbin.org/post"
//    }
//
//    ...调用结束了...

// ----------------------------------------------------------------------------------------------------------------------------------------------------------

//    get_run();
//    ...AxiosJava get-开始进行get请求操作...
//    请求url为 -> http://httpbin.org/get, 其body -> {studentId=1120131131, name=LC}
//    data2 -> {
//    "args": {
//    "name": "LC",
//    "studentId": "1120131131"
//    },
//    "headers": {
//    "Accept": "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
//    "Accept-Encoding": "gzip, deflate",
//    "Accept-Language": "zh-CN,zh;q=0.8",
//    "Host": "httpbin.org",
//    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool",
//    "X-Amzn-Trace-Id": "Root=1-605e02b2-6cb268ce1a2c0bf91ecb4077"
//    },
//    "origin": "112.65.11.112",
//    "url": "http://httpbin.org/get?studentId=1120131131&name=LC"
//    }
//
//    ...调用成功,data数据为->Success-请求的成功数据为->{
//    "args": {
//    "name": "LC",
//    "studentId": "1120131131"
//    },
//    "headers": {
//    "Accept": "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
//    "Accept-Encoding": "gzip, deflate",
//    "Accept-Language": "zh-CN,zh;q=0.8",
//    "Axiosjava-Token": "AxiosJava http",
//    "Host": "httpbin.org",
//    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool",
//    "X-Amzn-Trace-Id": "Root=1-605e02b2-508d008909cabd6e3fdd478c"
//    },
//    "origin": "112.65.11.112",
//    "url": "http://httpbin.org/get?studentId=1120131131&name=LC"
//    }
//
//    ...调用结束了...