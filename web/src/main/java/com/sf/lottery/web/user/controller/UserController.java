package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.UserService;
import com.sf.lottery.web.utils.CookiesUtil;
import com.sf.lottery.web.utils.HttpRequest;
import com.sf.lottery.web.websocket.WebsocketClientFactory;
import com.sf.lottery.web.weixin.domain.UserInfoReturn;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/1.
 */
@Controller
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private HttpRequest httpRequest;
    @Autowired
    private CookiesUtil cookiesUtil;
    @Autowired
    private UserService userService;

    @Value("signUp.websocket.address")
    private String signUpAddress;

    @ResponseBody
    @RequestMapping(value = "/user/getSignedUser", method = RequestMethod.POST)
    public JsonResult<List<User>> getSignedUser() {
        JsonResult<List<User>> result = new JsonResult<>();
        try {
            List<User> Users = userService.getSignedUser();
            result.setData(Users);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String getWXUserInfo(@RequestParam("sfnum") int sfnum, @RequestParam("sfname") String sfname, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = cookiesUtil.getCookieByName(request,"userJson");
        UserInfoReturn userInfoReturn = null;
        try {
            userInfoReturn = JSON.parseObject(URLDecoder.decode(cookie.getValue(),"UTF-8"),UserInfoReturn.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        boolean exists = userService.verifyUser(sfnum,sfname);
        if(exists){
            User user = new User();
            user.setSfNum(sfnum);
            user.setSfName(sfname);
            user.setWxSex(userInfoReturn.getSex());
            user.setWxOpenid(userInfoReturn.getOpenid());
            user.setWxCity(userInfoReturn.getCity());
            user.setWxCountry(userInfoReturn.getCountry());
            user.setWxHeadimgurl(userInfoReturn.getHeadimgurl());
            user.setWxNickname(userInfoReturn.getNickname());
            user.setWxProvince(userInfoReturn.getProvince());
            user.setWxPrivilege(userInfoReturn.getPrivilege());
            user.setWxUnionid(userInfoReturn.getUnionid());
            user.setIsSign(true);
            user.setSignedTime(new Date());
            int userId = 0;
            try {
                userId = userService.saveUser(user);
                cookiesUtil.addCookie(response,"userId",String.valueOf(userId),86400);
                WebSocketClient webSocketClient = WebsocketClientFactory.getWebsocketClient("signUp", signUpAddress);
                webSocketClient.connectBlocking();
                webSocketClient.send(JSON.toJSONString(user));
                webSocketClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/frontend/main.html";
        }else{
            return "该用户不存在";
        }
    }
}
