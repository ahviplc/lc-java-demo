package com.voovanDemo.socketDemo.test;

import com.voovanDemo.socketDemo.DependentClass.ServerHandlerTest;
import org.voovan.network.filter.StringFilter;
import org.voovan.network.messagesplitter.LineMessageSplitter;
import org.voovan.network.tcp.TcpServerSocket;


import java.io.IOException;

public class NioServerSocketTest {

    public static void main(String[] args) throws IOException {
        TcpServerSocket serverSocket = new TcpServerSocket("127.0.0.1", 2031, 30000, 1);
        serverSocket.handler(new ServerHandlerTest());
        serverSocket.filterChain().add(new StringFilter());

        serverSocket.messageSplitter(new LineMessageSplitter());
        // com.voovanDemo.socketDemo.DependentClass.LineMessageSplitter
        // serverSocket.messageSplitter(new LineMessageSplitter());

        serverSocket.start();
    }
}
