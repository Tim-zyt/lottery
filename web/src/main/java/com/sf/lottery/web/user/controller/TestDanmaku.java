package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/30.
 */
@Controller
public class TestDanmaku {
    private final static Logger log = LoggerFactory.getLogger(TestDanmaku.class);

    @ResponseBody
    @RequestMapping(value = "/danmuku/test", method = RequestMethod.POST)
    public JsonResult<Boolean> getWXUserInfo(@RequestParam("content") String content, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("danmuku", "ws://localhost/signUpChannel");
            webSocketClient.connectBlocking();
            webSocketClient.send(content);
            webSocketClient.close();
            result.setData(true);
        } catch (Exception e) {
            result.setData(false);
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
