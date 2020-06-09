package com.voovanDemo.socketDemo.test;

import com.voovanDemo.socketDemo.DependentClass.ServerBenchHandlerTest;
import org.voovan.network.tcp.TcpServerSocket;


import java.io.IOException;

public class NioServerSocketBenchTest {

    public static void main(String[] args) throws IOException {
        TcpServerSocket serverSocket = new TcpServerSocket("127.0.0.1",28080,30*1000);
        serverSocket.handler(new ServerBenchHandlerTest());
        serverSocket.start();
    }
}
