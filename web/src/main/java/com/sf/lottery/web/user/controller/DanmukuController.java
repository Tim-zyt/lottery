package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.damuku.domain.DanmukuMessage;
import com.sf.lottery.web.utils.CookiesUtil;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import com.sun.javafx.binding.StringFormatter;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/1.
 */
@Controller
public class DanmukuController {
    @Autowired
    private OperaController operaController;
    @Autowired
    private UserService userService;
    @Value("${danmuku.websocket.address}")
    private String danmukuAddress;
    private final static Logger log = LoggerFactory.getLogger(DanmukuController.class);

    @ResponseBody
    @RequestMapping(value = "/danmuku/addDanmuKu", method = RequestMethod.POST)
    public JsonResult<Boolean> getWXUserInfo(@RequestBody DanmukuMessage danmukuMessage, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            int userId = Integer.parseInt(CookiesUtil.getCookieByName(request, "userId").getValue());
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new IllegalAccessException();
            }
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("danmuku", danmukuAddress);
            webSocketClient.connectBlocking();
            danmukuMessage.setSfUserName(user.getSfName());
            danmukuMessage.setSfUserNum(String.format("%08d",user.getSfNum()));
            danmukuMessage.setWxAvatar(user.getWxHeadimgurl());
            switch (danmukuMessage.getType()){
                case 0:
                    //普通弹幕
                    break;
                case 1:
                    //鲜花
                    operaController.updateFlower();
                    break;
                case 2:
                    //跑车
                    operaController.updateCar();
                    break;
                case 3:
                    //火箭
                    operaController.updateRocket();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            webSocketClient.send(JSON.toJSONString(danmukuMessage));
            webSocketClient.close();
            result.setData(true);
        } catch (IllegalAccessException | NullPointerException i) {
            result.setData(false);
            result.setMessage("用户信息错误，请重新登陆");
            result.setErrCode(JsonResult.NEED_RE_LOGIN);
        } catch (Exception e) {
            result.setData(false);
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
