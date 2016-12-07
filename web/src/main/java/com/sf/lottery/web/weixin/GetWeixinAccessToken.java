package com.sf.lottery.web.weixin;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.common.utils.StrUtils;
import com.sf.lottery.web.utils.HttpRequest;
import com.sf.lottery.web.weixin.domain.AccessTokenReturn;
import com.sf.lottery.web.weixin.domain.JSApiTicketReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/3.
 */
@Controller
public class GetWeixinAccessToken {

    private final static Logger log = LoggerFactory.getLogger(GetWeixinAccessToken.class);
    @Autowired
    private HttpRequest httpRequest;
    @Value("${weixin.appID}")
    private String appId;
    @Value("${weixin.appsecret}")
    private String appSecret;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private volatile String accessToken;
    private final Object accessTokenSync = new Object();
    private volatile String jsApiTicket;
    private final Object jsApiTicketSync = new Object();

    //  单线程修改，只用volatile就可以
    private volatile int getAccessTokenFailedCount;
    private volatile int getJsApiTicketFailedCount;

    private void refreshAccessToken(final AccessTokenReturn accessTokenReturn) {
        log.info("刷新access token成功！新的access token为：{}",accessTokenReturn.getAccess_token());
        accessToken = accessTokenReturn.getAccess_token();
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = httpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", StrUtils.makeString("grant_type=client_credential&appid=", appId, "&secret=", appSecret));
                    AccessTokenReturn accessTokenReturn = JSON.parseObject(s, AccessTokenReturn.class);
                    if (!accessTokenReturn.isSuccessful()) {
                        throw new Exception("获取微信access token失败!" + accessTokenReturn.getErrmsg());
                    }
                    refreshAccessToken(accessTokenReturn);
                } catch (Exception e) {
                    AccessTokenReturn accessTokenReturn1 = new AccessTokenReturn();
                    accessTokenReturn1.setAccess_token(accessToken);
                    accessTokenReturn1.setExpires_in(31); //每隔一秒重试一次
                    log.warn(ExceptionUtils.getStackTrace(e));
                }
            }
        }, accessTokenReturn.getExpires_in() - 30, TimeUnit.SECONDS);//提前30s刷新，目前access_token的有效期通过返回的expire_in来传达，目前是7200秒之内的值。中控服务器需要根据这个有效时间提前去刷新新access_token。在刷新过程中，中控服务器对外输出的依然是老access_token，此时公众平台后台会保证在刷新短时间内，新老access_token都可用，这保证了第三方业务的平滑过渡
    }

    private void refreshJSApiTicket(final JSApiTicketReturn jsApiTicketReturn) {
        log.info("刷新js api ticket成功！新的js api ticket为{}",jsApiTicketReturn.getTicket());
        jsApiTicket = jsApiTicketReturn.getTicket();
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = httpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", StrUtils.makeString("access_token=", accessToken, "&type=jsapi"));
                    JSApiTicketReturn jsApiTicketReturn = JSON.parseObject(s, JSApiTicketReturn.class);
                    if (!jsApiTicketReturn.isSuccessful()) {
                        throw new Exception("获取微信js api ticket失败!" + jsApiTicketReturn.getErrmsg());
                    }
                    refreshJSApiTicket(jsApiTicketReturn);
                } catch (Exception e) {
                    JSApiTicketReturn jsApiTicketReturn = new JSApiTicketReturn();
                    jsApiTicketReturn.setTicket(jsApiTicket);
                    jsApiTicketReturn.setExpires_in(31); //每隔一秒重试一次
                    log.warn(ExceptionUtils.getStackTrace(e));
                }
            }
        }, jsApiTicketReturn.getExpires_in() - 30, TimeUnit.SECONDS);
    }

    private void initializeAccessToken() throws Exception {
        if (accessToken == null) {
            synchronized (accessTokenSync) {
                if (accessToken == null) {
                    String s = httpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", StrUtils.makeString("grant_type=client_credential&appid=", appId, "&secret=", appSecret));
                    AccessTokenReturn accessTokenReturn = JSON.parseObject(s, AccessTokenReturn.class);
                    if (!accessTokenReturn.isSuccessful()) {
                        throw new Exception("获取微信access token失败!" + accessTokenReturn.getErrmsg());
                    }
                    refreshAccessToken(accessTokenReturn);
                }
            }
        }
    }

    private void initializeJsApiTicket() throws Exception {
        if (jsApiTicket == null) {
            synchronized (jsApiTicketSync) {
                if (jsApiTicket == null) {
                    String s = httpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", StrUtils.makeString("access_token=", accessToken, "&type=jsapi"));
                    JSApiTicketReturn jsApiTicketReturn = JSON.parseObject(s, JSApiTicketReturn.class);
                    if (!jsApiTicketReturn.isSuccessful()) {
                        throw new Exception("获取微信js api ticket失败!" + jsApiTicketReturn.getErrmsg());
                    }
                    refreshJSApiTicket(jsApiTicketReturn);
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/weixin/accessToken", method = RequestMethod.GET)
    public String getWXAccessToken() throws Exception {
        initializeAccessToken();
        return accessToken;
    }

    @ResponseBody
    @RequestMapping(value = "/weixin/jsApiTicket", method = RequestMethod.GET)
    public String getWXJsApiTicket() throws Exception {
        initializeAccessToken();
        initializeJsApiTicket();
        return jsApiTicket;
    }

    @ResponseBody
    @RequestMapping(value = "/weixin/jsSignature", method = RequestMethod.GET)
    public String getWXJsSignature(@RequestParam("noncestr") String noncestr, @RequestParam("timestamp") String timestamp, @RequestParam("htmlPath") String htmlPath, HttpServletRequest request) throws Exception {
        initializeAccessToken();
        initializeJsApiTicket();
        String url = "";
        url = request.getScheme() +"://" + request.getServerName() + htmlPath;
        log.warn("jsApiTicket:"+jsApiTicket);
        log.warn("noncestr:"+noncestr);
        log.warn("timestamp"+timestamp);
        log.warn("url"+url);
        return StrUtils.sha1(StrUtils.makeString("jsapi_ticket=",jsApiTicket,"&noncestr=",noncestr,"&timestamp=",timestamp,"&url=",url));
    }
}
