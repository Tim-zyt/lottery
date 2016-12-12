package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.service.ConfigService;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.gift.UserShakeVo;
import com.sf.lottery.web.sort.NonThreadSafeDescSortedMostNList;
import com.sf.lottery.web.utils.CookiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 01139954 on 2016/12/8.
 */
@Controller
public class ShakeController {
    private final static Logger log = LoggerFactory.getLogger(ShakeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigService configService;

    //singleton
    private Map<Integer,UserShakeVo> shakeCountMap = new ConcurrentHashMap<>();


    @ResponseBody
    @RequestMapping(value = "/shake/saveShakeCount", method = RequestMethod.POST)
    public JsonResult<String> saveShakeCount(@RequestParam("shakeCount") int shakeCount, HttpServletRequest request){
        JsonResult<String> result = new JsonResult<>();
        Cookie cookie = CookiesUtil.getCookieByName(request, "userId");
        if(cookie == null){
            log.info("用户未登陆");
            result.setData("亲！您还未登陆");
            return result;
        }
        try {
            String userIdStr = cookie.getValue();
            int userId = Integer.parseInt(userIdStr);
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
    @RequestMapping(value = "/shake/getTopN", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping(value = "/shake/isCanShark", method = RequestMethod.POST)
    public JsonResult<Boolean> isStartShake(){
        JsonResult<Boolean> result = new JsonResult<>();
        result.setData(configService.isCanShark());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/shake/openShark", method = RequestMethod.POST)
    public JsonResult<Boolean> startShark(){
        JsonResult<Boolean> result = new JsonResult<>();
        result.setData(configService.openShark() > 0);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/shake/closeShark", method = RequestMethod.POST)
    public JsonResult<Boolean> closeShark(){
        JsonResult<Boolean> result = new JsonResult<>();
        result.setData(configService.closeShark() > 0);
        return result;
    }

}
