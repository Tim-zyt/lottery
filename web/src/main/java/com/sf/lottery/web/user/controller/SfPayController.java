package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/23.
 */
@Controller
public class SfPayController {
    private final static Logger log = LoggerFactory.getLogger(SfPayController.class);

    @Autowired
    private ConfigService configService;

    @ResponseBody
    @RequestMapping(value = "/pay/isCanSfPay", method = RequestMethod.POST)
    public JsonResult<Integer> isStartSfPay(){
        JsonResult<Integer> result = new JsonResult<>();
        int temp = 0;
        try {
            temp = configService.getCurSfPay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(temp);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/pay/startSfPay0", method = RequestMethod.POST)
    public JsonResult<Boolean> startSfPay0(){
        JsonResult<Boolean> result = new JsonResult<>();
        boolean temp = true;
        try {
            temp = configService.setCurSfPay(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(temp);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/pay/startSfPay1", method = RequestMethod.POST)
    public JsonResult<Boolean> startSfPay1(){
        JsonResult<Boolean> result = new JsonResult<>();
        boolean temp = true;
        try {
            temp = configService.setCurSfPay(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(temp);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/pay/startSfPay2", method = RequestMethod.POST)
    public JsonResult<Boolean> startSfPay2(){
        JsonResult<Boolean> result = new JsonResult<>();
        boolean temp = true;
        try {
            temp = configService.setCurSfPay(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(temp);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/pay/startSfPay3", method = RequestMethod.POST)
    public JsonResult<Boolean> startSfPay3(){
        JsonResult<Boolean> result = new JsonResult<>();
        boolean temp = true;
        try {
            temp = configService.setCurSfPay(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(temp);
        return result;
    }
}
