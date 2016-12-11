package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.Award;
import com.sf.lottery.common.model.Couple;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.vo.CpGiftVo;
import com.sf.lottery.service.CoupleService;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.gift.CpGiftMessage;
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
 * Created by 01139954 on 2016/12/11.
 */
@Controller
public class CpGiftController {
    private final static Logger log = LoggerFactory.getLogger(CpGiftController.class);

    @Autowired
    private CoupleService coupleService;

    @Value("${cpGift.websocket.address}")
    private String cpGiftAddress;

    @ResponseBody
    @RequestMapping(value = "/cpGift/init", method = RequestMethod.POST)
    public JsonResult<List<CpGiftVo>> initCpGift(){
        JsonResult<List<CpGiftVo>> result = new JsonResult<List<CpGiftVo>>();
        try {
            result.setData(coupleService.getAllUnAwardCouple());
        } catch (Exception e) {
            log.info(e.getMessage());
            return result;
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cpGift/start", method = RequestMethod.POST)
    public  String startCpGift(){
        try {
            CpGiftMessage cpGiftMessage = new CpGiftMessage();
            cpGiftMessage.setFlag(0);
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("cpGift", cpGiftAddress);
            webSocketClient.connectBlocking();
            webSocketClient.send(JSON.toJSONString(cpGiftMessage));
            webSocketClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/cpGift/end", method = RequestMethod.POST)
    public String endCpGift(){
        try {
            CpGiftVo luckCp = coupleService.getLuckCP();
            CpGiftMessage cpGiftMessage = new CpGiftMessage();
            cpGiftMessage.setFlag(1);
            cpGiftMessage.setLuckCP(luckCp);
            WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("cpGift", cpGiftAddress);
            webSocketClient.connectBlocking();
            webSocketClient.send(JSON.toJSONString(cpGiftMessage));
            webSocketClient.close();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

}
