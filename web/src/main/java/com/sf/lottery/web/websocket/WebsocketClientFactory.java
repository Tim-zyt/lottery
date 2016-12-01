package com.sf.lottery.web.websocket;

import com.sf.lottery.common.utils.StrUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 由于Dubbox依赖于spring3.x，但是spring对于websocket支持是从4.x开始的，所以通过本地建立client访问websocket
 *
 * @author Hash Zhang
 * @version 1.0.0
 * @// TODO: 2016/11/30 未来有机会的话，要升级成spring4.x，时间来不及了。。。
 * @date 2016/11/30.
 */
public class WebsocketClientFactory {
    public static WebSocketClient getWebsocketClient(final String name, final String uri) throws URISyntaxException {
        return new WebSocketClient(new URI(uri), new Draft_17()) {
            private final Logger log = LoggerFactory.getLogger(WebSocketClient.class);
            @Override
            public void onMessage(String message) {
            }

            @Override
            public void onOpen(ServerHandshake handshake) {
                log.info(StrUtils.makeString("websocket client [",name,"] is established!"));
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                log.info(StrUtils.makeString("websocket client [",name,"] is closed!"));
            }

            @Override
            public void onError(Exception ex) {
                log.warn(ExceptionUtils.getFullStackTrace(ex));
            }
        };
    }
}
