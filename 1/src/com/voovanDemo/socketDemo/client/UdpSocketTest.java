package com.voovanDemo.socketDemo.client;

import com.voovanDemo.socketDemo.DependentClass.ClientHandlerTest;
import org.voovan.network.filter.StringFilter;
import org.voovan.network.messagesplitter.LineMessageSplitter;
import org.voovan.network.udp.UdpSocket;
import org.voovan.tools.log.Logger;
/**
 * 类文字命名
 *
 * @author helyho
 * <p>
 * Voovan Framework.
 * WebSite: https://github.com/helyho/Voovan
 * Licence: Apache v2 License
 */
public class UdpSocketTest {

    public static void main(String[] args) throws Exception {
        UdpSocket udpSocket = new UdpSocket("127.0.0.1", 60000, 5000, 1);
        udpSocket.messageSplitter(new LineMessageSplitter());
        udpSocket.filterChain().add(new StringFilter());
        udpSocket.handler(new ClientHandlerTest());
        udpSocket.start();
    }
}