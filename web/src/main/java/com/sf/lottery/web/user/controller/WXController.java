package com.sf.lottery.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.web.utils.HttpRequest;
import com.sf.lottery.web.weixin.domain.UserAuthorizationReturn;
import com.sf.lottery.web.weixin.domain.UserInfoReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/29.
 */
@Controller
public class WXController {
    private final static Logger log = LoggerFactory.getLogger(WXController.class);
    @Autowired
    private HttpRequest httpRequest;
    @Value("${weixin.appID}")
    private String appId;
    @Value("${weixin.appsecret}")
    private String appSecret;

    @ResponseBody
    @RequestMapping(value = "/weixin/message", method = RequestMethod.GET)
    public String getWXUserInfo(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        //加解密省略。。。直接返回成功
        return echostr;
    }

    @RequestMapping(value = "/weixin/login", method = RequestMethod.GET)
    public String getWXUserInfo(@RequestParam("code") String code) {
        try {
            String s = httpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
                    "appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code");
            UserAuthorizationReturn userAuthorizationReturn = JSON.parseObject(s, UserAuthorizationReturn.class);
            s = httpRequest.sendGet("https://api.weixin.qq.com/sns/userinfo",
                    "access_token="+userAuthorizationReturn.getAccess_token()+"&openid="+userAuthorizationReturn.getOpenid()+"&lang=zh_CN");
            UserInfoReturn userInfoReturn = JSON.parseObject(s, UserInfoReturn.class);
            System.out.println(userInfoReturn.toString());
            return "redirect:/frontend/test.html";
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

}
