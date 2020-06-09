package com.voovanDemo.socketDemo.DependentClass;

import org.voovan.network.IoSession;
import org.voovan.network.MessageSplitter;

import java.nio.ByteBuffer;

/**
 * 按换行对消息分割
 *
 * @author helyho
 */
public class LineMessageSplitter implements MessageSplitter {

    @Override
    public int canSplite(IoSession session, ByteBuffer byteBuffer) {
        byteBuffer.position(byteBuffer.limit() - 1);
        byte lastByte = byteBuffer.get();
        byteBuffer.position(0);
        if (byteBuffer.limit() > 1 && lastByte == '\n') {
            return byteBuffer.limit();
        }
        return -1;
    }
}