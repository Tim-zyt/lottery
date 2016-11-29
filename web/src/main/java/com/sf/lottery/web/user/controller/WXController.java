package com.sf.lottery.web.user.controller;

import com.sf.lottery.web.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/29.
 */
@Controller
public class WXController {
    @Autowired
    private HttpRequest httpRequest;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getWXUserInfo(@RequestParam("code") String code) {
        try {
            httpRequest.sendGet("http://api.weixin.qq.com/sns/oauth2/access_token",
                    "appid=wx0c7b8ab55037d5ca&secret=2be53e2a455ec83353eb5a8865844e57&code=" + code + "&grant_type=authorization_code");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/frontend/test.html";
    }
}
