package com.sf.lottery.web.websocket;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/30.
 */
public class WebsocketChannel extends WebSocketServlet {
    private ConcurrentHashSet<MyMessageInbound> mmiList = new ConcurrentHashSet<>();

    @Override
    protected StreamInbound createWebSocketInbound(String s, HttpServletRequest httpServletRequest) {
        return new MyMessageInbound();
    }

    private class MyMessageInbound extends MessageInbound {
        WsOutbound myoutbound;

        @Override
        public void onOpen(WsOutbound outbound) {
                this.myoutbound = outbound;
                mmiList.add(this);
        }

        @Override
        public void onClose(int status) {
            mmiList.remove(this);
        }

        @Override
        public void onTextMessage(CharBuffer cb) throws IOException {
            for (MyMessageInbound mmib : mmiList) {
                CharBuffer buffer = CharBuffer.wrap(cb);
                try {
                    mmib.myoutbound.writeTextMessage(buffer);
                    mmib.myoutbound.flush();
                }catch (Exception e){
                }
            }
        }

        @Override
        public void onBinaryMessage(ByteBuffer bb) throws IOException {
        }

        @Override
        public int getReadTimeout() {
            return 0;
        }
    }
}
