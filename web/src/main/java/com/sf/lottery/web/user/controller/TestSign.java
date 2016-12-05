package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 01139954 on 2016/12/5.
 */
@Controller
public class TestSign {
    private final static Logger log = LoggerFactory.getLogger(TestSign.class);

    @Value("${signUp.websocket.address}")
    private String signUpAddress;

    @ResponseBody
    @RequestMapping(value = "/sign/test", method = RequestMethod.GET)
    public JsonResult<Boolean> getSignUser(@RequestParam("content") String content, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            WebSocketClient webSocketClient = WebsocketClientFactory
                    .getWebsocketClient("signUp", signUpAddress);
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
