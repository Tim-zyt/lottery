package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/8.
 */
@Controller
public class ConfigController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ConfigService configService;

    @ResponseBody
    @RequestMapping(value = "/config/setCurrentGift", method = RequestMethod.POST)
    public JsonResult<Boolean> setCurrentAward(@RequestParam("awardId") int awardId){
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            result.setData(configService.setCurrentAward(awardId));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/config/setCurrentOpera", method = RequestMethod.POST)
    public JsonResult<Boolean> setCurrentOpera(@RequestParam("operaId") int operaId){
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            result.setData(configService.setCurrentOpera(operaId));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
