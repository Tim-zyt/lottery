package com.sf.lottery.web.weixin.domain;

import java.io.Serializable;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/3.
 */
public class AccessTokenReturn extends BaseReturn{
    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
