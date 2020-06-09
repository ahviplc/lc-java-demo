package com.voovanDemo.socketDemo.DependentClass;

import org.voovan.network.ConnectType;
import org.voovan.network.HeartBeat;
import org.voovan.network.IoHandler;
import org.voovan.network.IoSession;
import org.voovan.tools.log.Logger;


/**
 * 客户端业务句柄类
 **/
public class ServerHandlerTest implements IoHandler {
    @Override
    public Object onConnect(IoSession session) {
        if (!(session.socketContext().getConnectType() == ConnectType.UDP)) {
            HeartBeat heartBeat = session.getHeartBeat();
            if (heartBeat == null) {
                heartBeat = HeartBeat.attachSession(session, "PINGq", "PONGq");
            }
        }
        Logger.simple("========================onConnect========================");
        return null;
    }

    @Override
    public void onDisconnect(IoSession session) {
        Logger.simple("onDisconnect");
    }

    @Override
    public Object onReceive(IoSession session, Object obj) {
        Logger.simple(session.remoteAddress() + ":" + session.remotePort());
        Logger.simple("Server onRecive: " + obj.toString());
        return "==== " + obj.toString().trim() + " LC " + " ===== \r\n";
    }

    @Override
    public void onException(IoSession session, Exception e) {
        e.printStackTrace();
        Logger.error("Server exception", e);
        session.close();
    }

    /**
     * 空闲事件,根据启动连接时的参数如果这个事件内没有进行 send 和 read 操作则会触发这个空闲事件
     * session: 会话类对象。
     *
     * @param session
     */
    @Override
    public void onIdle(IoSession session) {
        //心跳依赖于 idle 时间,这个参数在构造 socket 的时候设置具体查看org.voovan.network.aio.AioServerSocket
        System.out.println("==onIdle==我是闲啊==com.voovanDemo.socketDemo.DependentClass.ServerHandlerTest==");
        //服务端和客户端使用了两种不同的心跳绑定方式,这是其中一种
        //心跳绑定到 Session, 绑定过一次以后每次返回的都是第一次绑定的对象
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
        Logger.simple("Server onSent: " + sad);
        //jmeter 测试是需要打开,和客户端测试时关闭
//		session.close();
    }

    @Override
    public void onFlush(IoSession session) {
        System.out.println("===onFlush===");
    }
}