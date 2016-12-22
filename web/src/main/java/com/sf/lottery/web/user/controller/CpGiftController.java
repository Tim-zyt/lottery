package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.vo.CpGiftVo;
import com.sf.lottery.service.ConfigService;
import com.sf.lottery.service.CoupleService;
import com.sf.lottery.web.gift.CpGiftMessage;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private ConfigService configService;

    private CpGiftVo cacheLuckCp = new CpGiftVo();

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

    //节目管理页面的“开始CP抽奖”按钮的事件
    @ResponseBody
    @RequestMapping(value = "/cpGift/start", method = RequestMethod.POST)
    public  String startCpGift(){
        try {
            configService.setCurStateCp(1);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    //节目管理页面的“结束CP抽奖”按钮的事件
    @ResponseBody
    @RequestMapping(value = "/cpGift/end", method = RequestMethod.POST)
    public JsonResult<CpGiftVo> endCpGift(){
        JsonResult<CpGiftVo> result = new JsonResult<>();
        try {
            CpGiftVo luckCp = coupleService.getLuckCP();
            cacheLuckCp = luckCp;
            configService.setCurStateCp(2);
            result.setData(luckCp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cpGift/getCacheLuckCp", method = RequestMethod.POST)
    public JsonResult<CpGiftVo> getCacheLuckCp(){
        JsonResult<CpGiftVo> result = new JsonResult<>();
        try {
            result.setData(cacheLuckCp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @ResponseBody
    @RequestMapping(value = "/cpGift/deleteCpWinners", method = RequestMethod.POST)
    public JsonResult<Boolean> deleteCpWinners(@RequestParam("cpWinnersId") int cpWinnersId){
        JsonResult<Boolean> result = new JsonResult<>();
        result.setData(coupleService.deleteCouple(cpWinnersId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cpGift/unConfirmCPImg", method = RequestMethod.POST)
    public JsonResult<Integer> unConfirmCPImg(@RequestParam("cpId") int cpId){
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(coupleService.unConfirmCPImg(cpId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cpGift/confirmCPImg", method = RequestMethod.POST)
    public JsonResult<Integer> confirmCPImg(@RequestParam("cpId") int cpId){
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(coupleService.confirmCPImg(cpId));
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/cpGift/getAllCP", method = RequestMethod.POST)
    public JsonResult<List<CpGiftVo>> getAllCP(){
        JsonResult<List<CpGiftVo>> result = new JsonResult<List<CpGiftVo>>();
        try {
            result.setData(coupleService.getAllCouple());
        } catch (Exception e) {
            log.info(e.getMessage());
            return result;
        }
        return result;
    }
}
