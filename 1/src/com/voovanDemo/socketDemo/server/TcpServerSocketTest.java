package com.voovanDemo.socketDemo.server;

import java.io.IOException;

import com.voovanDemo.socketDemo.DependentClass.ServerHandlerTest;
import org.voovan.network.tcp.TcpServerSocket;
import org.voovan.network.filter.StringFilter;

public class TcpServerSocketTest {
    public static void main(String[] args) throws IOException {
        /**
         * 构造函数
         * 		默认不会出发空闲事件, 默认发超时时间: 1s
         * @param host      监听地址
         * @param port        监听端口
         * @param readTimeout   超时时间, 单位: 毫秒
         * @throws IOException    异常
         */
        // TcpServerSocket serverSocket = new TcpServerSocket("127.0.0.1", 2031, 50000); //构造服务端类实例

        /**
         * 构造函数
         *      默认发超时时间: 1s
         * @param host      监听地址
         * @param port        监听端口
         * @param idleInterval    空闲事件触发时间, 单位: 秒
         * @param readTimeout   超时时间, 单位: 毫秒
         * @throws IOException    异常
         */
        //TcpServerSocket serverSocket = new TcpServerSocket("127.0.0.1", 2031, 50000, 1); //构造服务端类实例

        /**
         * 构造函数
         * @param host      监听地址
         * @param port        监听端口
         * @param idleInterval    空闲事件触发时间, 单位: 秒
         * @param readTimeout   超时时间, 单位: 毫秒 根据实际业务需求 调整此时间
         * @param sendTimeout 发超时时间, 单位: 毫秒
         * @throws IOException    异常
         */
        TcpServerSocket serverSocket = new TcpServerSocket("127.0.0.1", 2031, 50000, 5000, 1); //构造服务端类实例
        // 获取空闲事件时间 默认是0 不启用 单位:秒
        System.out.println("获取空闲事件时间: " + serverSocket.getIdleInterval());
        serverSocket.handler(new ServerHandlerTest());       //设置业务处理句柄
        serverSocket.filterChain().add(new StringFilter());  //设置消息过滤器
        serverSocket.start();                                //启动服务类
    }
}
