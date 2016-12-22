package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.Award;
import com.sf.lottery.common.model.User;
import com.sf.lottery.service.AwardService;
import com.sf.lottery.service.ConfigService;
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

import java.util.ArrayList;
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

    @Autowired
    private ConfigService configService;

    private List<User> cacheUsers = new ArrayList<>();

    //节目管理页面的“开始抽奖”按钮的事件
    @ResponseBody
    @RequestMapping(value = "/gift/start", method = RequestMethod.POST)
    public String startGift(){
        try {
            configService.setCurStateAward(1);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    //节目管理页面的“结束抽奖”按钮的事件
    @ResponseBody
    @RequestMapping(value = "/gift/end", method = RequestMethod.POST)
    public JsonResult<List<User>> endGift(){
        JsonResult<List<User>> result = new JsonResult<>();
        try {
            List<User> users = userService.getAwardUser();
            cacheUsers = users;
            configService.setCurStateAward(2);
            result.setData(users);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/gift/getLuckManCount", method = RequestMethod.POST)
    public JsonResult<Integer> getLuckManCount(){
        JsonResult<Integer> result = new JsonResult<>();
        try {
            Award curAward = awardService.getCurAward();
            if(curAward == null){
                result.setData(0);
                return result;
            }
            result.setData(curAward.getAwUserCount());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/gift/getCacheLuckMans", method = RequestMethod.POST)
    public JsonResult<List<User>> getCacheLuckMans(){
        JsonResult<List<User>> result = new JsonResult<>();
        try {
            result.setData(cacheUsers);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
