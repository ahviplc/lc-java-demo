package com.voovanDemo.socketDemo.test;

import com.voovanDemo.socketDemo.DependentClass.ClientHandlerTest;
import org.voovan.network.filter.StringFilter;
import org.voovan.network.messagesplitter.LineMessageSplitter;
import org.voovan.network.tcp.TcpSocket;


public class NioSocketTest {

    public static void main(String[] args) throws Exception {
        TcpSocket socket = new TcpSocket("127.0.0.1", 2031, 5000 * 60, 1);
        socket.handler(new ClientHandlerTest());
        socket.filterChain().add(new StringFilter());
        socket.messageSplitter(new LineMessageSplitter());
        socket.start();
    }
}