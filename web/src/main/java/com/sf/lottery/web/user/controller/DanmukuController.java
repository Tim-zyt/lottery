package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.web.damuku.domain.DanmukuMessage;
import com.sf.lottery.web.utils.CookiesUtil;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/1.
 */
@Controller
public class DanmukuController {

    @Value("danmuku.websocket.address")
    private String danmukuAddress;
    private final static Logger log = LoggerFactory.getLogger(DanmukuController.class);

    @ResponseBody
    @RequestMapping(value = "/danmuku/addDanmuKu", method = RequestMethod.POST)
    public JsonResult<Boolean> getWXUserInfo(@RequestBody DanmukuMessage danmukuMessage, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            int userId = Integer.parseInt(CookiesUtil.getCookieByName(request, "userId").getValue());

            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("danmuku", danmukuAddress);
            webSocketClient.connectBlocking();
            webSocketClient.send(JSON.toJSONString(danmukuMessage));
            webSocketClient.close();
            result.setData(true);
        } catch (Exception e) {
            result.setData(false);
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
