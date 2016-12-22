package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.ConfigService;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.damuku.domain.DanmukuMessage;
import com.sf.lottery.web.utils.CookiesUtil;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/1.
 */
@Controller
public class DanmukuController {
    private AtomicInteger count = new AtomicInteger(0);
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public DanmukuController() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                count.set(0);
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    private ThreadLocal<WebSocketClient> danmuKuClientPool = new ThreadLocal<WebSocketClient>() {
        @Override
        protected WebSocketClient initialValue() {
            try {
                WebSocketClient danmuku = WebsocketClientFactory.getWebsocketClient("danmuku", danmukuAddress);
                danmuku.connectBlocking();
                return danmuku;
            } catch (Exception e) {
                log.warn(ExceptionUtils.getStackTrace(e));
            }
            return null;
        }
    };
    @Autowired
    private ConfigService configService;
    @Autowired
    private OperaController operaController;
    @Autowired
    private UserService userService;
    @Value("${danmuku.websocket.address}")
    private String danmukuAddress;
    private final static Logger log = LoggerFactory.getLogger(DanmukuController.class);

    @ResponseBody
    @RequestMapping(value = "/danmuku/addDanmuKu", method = RequestMethod.POST)
    public JsonResult<Boolean> addDanmuKu(@RequestBody DanmukuMessage danmukuMessage, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            int userId = Integer.parseInt(CookiesUtil.getCookieByName(request, "userId").getValue());
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new IllegalAccessException();
            }

            switch (danmukuMessage.getType()) {
                case 0:
                    //普通弹幕
                    result.setData(true);
                    break;
                case 1:
                    //鲜花
                    if (configService.isCanReward()) {
                        operaController.updateFlower();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                case 2:
                    //跑车
                    if (configService.isCanReward()) {
                        operaController.updateCar();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                case 3:
                    //火箭
                    if (configService.isCanReward()) {
                        operaController.updateRocket();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (count.incrementAndGet() > 1) {

            } else {
                if (result.getData()) {
                    WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("danmuku", danmukuAddress);
                    webSocketClient.connectBlocking();
                    danmukuMessage.setSfUserName(user.getSfName());
                    danmukuMessage.setSfUserNum(String.format("%08d", user.getSfNum()));
                    danmukuMessage.setWxAvatar(user.getWxHeadimgurl());
                    webSocketClient.send(JSON.toJSONString(danmukuMessage));
                    webSocketClient.close();
                }
            }
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


    @ResponseBody
    @RequestMapping(value = "/test/addDanmuKu", method = RequestMethod.GET)
    public JsonResult<Boolean> testAddDanmuKu(@RequestParam("userId") int userId, @RequestParam("type") int type, @RequestParam("content") String content, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new IllegalAccessException();
            }

            switch (type) {
                case 0:
                    //普通弹幕
                    result.setData(true);
                    break;
                case 1:
                    //鲜花
                    if (configService.isCanReward()) {
                        operaController.updateFlower();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                case 2:
                    //跑车
                    if (configService.isCanReward()) {
                        operaController.updateCar();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                case 3:
                    //火箭
                    if (configService.isCanReward()) {
                        operaController.updateRocket();
                        result.setData(true);
                    } else {
                        result.setData(false);
                        result.setMessage("现在不能打赏");
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (count.incrementAndGet() > 1) {

            } else {
                DanmukuMessage danmukuMessage = new DanmukuMessage();
                danmukuMessage.setType(type);
                danmukuMessage.setContent(content);
                if (result.getData()) {
                    danmukuMessage.setSfUserName(user.getSfName());
                    danmukuMessage.setSfUserNum(String.format("%08d", user.getSfNum()));
                    danmukuMessage.setWxAvatar(user.getWxHeadimgurl());
                    try {
                        danmuKuClientPool.get().send(JSON.toJSONString(danmukuMessage));
                    } catch (Exception e) {
                        log.warn(ExceptionUtils.getStackTrace(e));
                        try {
                            danmuKuClientPool.get().close();
                        } catch (Exception e1) {

                        }
                        WebSocketClient danmuku = WebsocketClientFactory.getWebsocketClient("danmuku", danmukuAddress);
                        danmuku.connectBlocking();
                        danmuKuClientPool.set(danmuku);
                        danmuKuClientPool.get().send(JSON.toJSONString(danmukuMessage));
                    }
                }
            }
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
