package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.Award;
import com.sf.lottery.common.model.User;
import com.sf.lottery.service.AwardService;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.gift.GiftMessage;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 01139954 on 2016/12/3.
 */
@Controller
public class GiftController {
    private final static Logger log = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AwardService awardService;
    
    @Value("${gift.websocket.address}")
    private String giftAddress;

    @ResponseBody
    @RequestMapping(value = "/gift/start", method = RequestMethod.POST)
    public String startGift(){
        try {
            Award curAward = awardService.getCurAward();
            if(curAward == null){
                return "false";
            }
            GiftMessage giftMessage = new GiftMessage();
            giftMessage.setFlag(0);
            giftMessage.setLuckmanCount(curAward.getAwUserCount());
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("gift", giftAddress);
            webSocketClient.connectBlocking();
            webSocketClient.send(JSON.toJSONString(giftMessage));
            webSocketClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/gift/end", method = RequestMethod.POST)
    public JsonResult<List<User>> endGift(){
        JsonResult<List<User>> result = new JsonResult<>();
        try {
            List<User> users = userService.getAwardUser();
            GiftMessage giftMessage = new GiftMessage();
            giftMessage.setFlag(1);
            giftMessage.setLuckmanCount(users.size());
            giftMessage.setLuckmans(users);
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("gift", giftAddress);
            webSocketClient.connectBlocking();
            webSocketClient.send(JSON.toJSONString(giftMessage));
            webSocketClient.close();
            result.setData(users);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
