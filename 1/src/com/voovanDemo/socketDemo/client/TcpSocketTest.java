package com.voovanDemo.socketDemo.client;

import com.voovanDemo.socketDemo.DependentClass.ClientHandlerTest;
import org.voovan.network.tcp.TcpSocket;
import org.voovan.network.filter.StringFilter;

import java.io.IOException;

public class TcpSocketTest {
    public static void main(String[] args) throws Exception {
        /**
         * socket 连接
         * 		默认不会出发空闲事件, 默认发超时时间: 1s
         * @param host      监听地址
         * @param port        监听端口
         * @param readTimeout   超时时间, 单位: 毫秒
         * @throws IOException    IO异常
         */
        //TcpSocket socket = new TcpSocket("127.0.0.1", 2031, 50000); //构造客户端类实例

        /**
         * socket 连接
         *      默认发超时时间: 1s
         * @param host      监听地址
         * @param port        监听端口
         * @param idleInterval    空闲事件触发时间, 单位: 秒
         * @param readTimeout   超时时间, 单位: 毫秒
         * @throws IOException    IO异常
         */
        //TcpSocket socket = new TcpSocket("127.0.0.1", 2031, 50000, 1); //构造客户端类实例

        /**
         * socket 连接
         * @param host      监听地址
         * @param port        监听端口
         * @param idleInterval    空闲事件触发时间, 单位: 秒 默认是0不触发
         * @param readTimeout   超时时间, 单位: 毫秒
         * @param sendTimeout 发超时时间, 单位: 毫秒
         * @throws IOException    IO异常
         */
        TcpSocket socket = new TcpSocket("127.0.0.1", 2031, 50000, 5000, 3); //构造客户端类实例

        // 获取空闲事件时间 默认是0 不启用 单位:秒
        System.out.println("获取空闲事件时间: " + socket.getIdleInterval());
        // 设置会话读缓冲区大小
        // socket.setReadBufferSize(1 * 1204);
        socket.handler(new ClientHandlerTest());                //设置业务处理句柄
        socket.filterChain().add(new StringFilter());           //设置消息过滤器
        socket.start();                                         //启动服务类
    }
}
