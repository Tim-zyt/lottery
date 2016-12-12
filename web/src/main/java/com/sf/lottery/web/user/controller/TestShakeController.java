package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.service.ConfigService;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.gift.UserShakeVo;
import com.sf.lottery.web.sort.NonThreadSafeDescSortedMostNList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 01139954 on 2016/12/12.
 */
@Controller
public class TestShakeController {
    private final static Logger log = LoggerFactory.getLogger(TestShakeController.class);

    @Autowired
    private UserService userService;


    //singleton
    private Map<Integer,UserShakeVo> shakeCountMap = new ConcurrentHashMap<>();


    @ResponseBody
    @RequestMapping(value = "/testShake/saveShakeCount", method = RequestMethod.GET)
    public JsonResult<String> saveShakeCount(@RequestParam("shakeCount") int shakeCount,@RequestParam("userId") int userId, HttpServletRequest request){
        JsonResult<String> result = new JsonResult<>();
        try {
            if(shakeCountMap.containsKey(userId)){
                UserShakeVo curUserShakeVo = shakeCountMap.get(userId);
                curUserShakeVo.setShakeCount(shakeCount);
                shakeCountMap.put(userId, curUserShakeVo);//存的是引用，不用写这一句，只是为了阅读方便
            }else {
                User user = userService.getUserById(userId);
                UserShakeVo userShakeVo = new UserShakeVo();
                userShakeVo.setUserId(user.getId());
                userShakeVo.setUserName(user.getSfName());
                userShakeVo.setHeadImgUrl(user.getWxHeadimgurl());
                userShakeVo.setUserNo(user.getSfNum());//工号
                userShakeVo.setShakeCount(shakeCount);//摇的次数
                shakeCountMap.put(userId,userShakeVo);//重新放一个相同的key,会自动覆盖value
                result.setData("true");
            }
        } catch (Exception e) {
            log.info("该用户不存在");
            e.printStackTrace();
            result.setData("该用户不存在");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/testShake/getTopN", method = RequestMethod.GET)
    public JsonResult<LinkedList<Comparable>> getTopN(@RequestParam("topSize") int topSize){
        JsonResult<LinkedList<Comparable>> result = new JsonResult<>();
        NonThreadSafeDescSortedMostNList sortList = new NonThreadSafeDescSortedMostNList(topSize);
        Map<Integer, UserShakeVo> temp = new HashMap<>();
        temp.putAll(shakeCountMap);
        Iterator<Map.Entry<Integer, UserShakeVo>> entries = temp.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, UserShakeVo> entry = entries.next();
            sortList.put(entry.getValue());
        }
        result.setData(sortList.getResult());
        return result;
    }


}
