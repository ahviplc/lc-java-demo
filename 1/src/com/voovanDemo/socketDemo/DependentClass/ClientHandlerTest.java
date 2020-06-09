package com.voovanDemo.socketDemo.DependentClass;


import org.voovan.network.ConnectType;
import org.voovan.network.HeartBeat;
import org.voovan.network.IoHandler;
import org.voovan.network.IoSession;
import org.voovan.tools.TEnv;
import org.voovan.tools.log.Logger;

/**
 * 客户端业务句柄类
 **/
public class ClientHandlerTest implements IoHandler {

    private int reciveCount = 0;

    @Override
    public Object onConnect(IoSession session) {
        reciveCount = 0;
        System.out.println("onConnect");
        System.out.println("Connect from: " + session.remoteAddress() + ":" + session.remotePort() + " " + session.loaclPort());
        session.setAttribute("key", "attribute value");
        String msg = new String("test message\r\n");

        if (!(session.socketContext().getConnectType() == ConnectType.UDP)) {
            HeartBeat heartBeat = session.getHeartBeat();
            if (heartBeat == null) {
                //心跳绑定到 Session
                heartBeat = HeartBeat.attachSession(session, "PINGq", "PONGq");
            }
        }
        return msg;
    }

    @Override
    public void onDisconnect(IoSession session) {
        System.out.println("onDisconnect");
    }

    @Override
    public Object onReceive(IoSession session, Object obj) {
        System.out.println("Recive from: " + session.remoteAddress() + ":" + session.remotePort() + " " + session.loaclPort());
        //+"["+session.remoteAddress()+":"+session.remotePort()+"]"
        System.out.println("Client onRecive: " + obj.toString());
        System.out.println("Attribute onRecive: " + session.getAttribute("key"));
        TEnv.sleep(2000);
        reciveCount++;

        if (reciveCount >= 50) {
            session.close();
        }
        return "some data LC\r\n";
    }

    @Override
    public void onException(IoSession session, Exception e) {
        System.out.println("Client exception: " + e.getClass() + " => " + e.getMessage());
        Logger.error(e);
        session.close();
    }

    @Override
    public void onIdle(IoSession session) {
        //心跳依赖于 idle 时间,这个参数在构造 socket 的时候设置具体查看 AioSocket
        System.out.println("==onIdle==我是闲啊==com.voovanDemo.socketDemo.DependentClass.ClientHandlerTest==");
        //服务端和客户端使用了两种不同的心跳绑定方式,这是其中一种
        if (!(session.socketContext().getConnectType() == ConnectType.UDP)) {
            HeartBeat heartBeat = session.getHeartBeat();
            //心跳一次, 返回 true:本次心跳成功, false: 本次心跳失败
            System.out.println("HB心跳==>" + heartBeat.beat(session));
            System.out.println("HB心跳失败次数==>" + heartBeat.getFailedCount());
            if (heartBeat.getFailedCount() >= 50) {
                session.close();
            }
        }
    }

    @Override
    public void onSent(IoSession session, Object obj) {
        String sad = (String) obj;
        System.out.println("Client onSent: " + sad);
    }

    @Override
    public void onFlush(IoSession session) {
        System.out.println("===onFlush===");
    }
}