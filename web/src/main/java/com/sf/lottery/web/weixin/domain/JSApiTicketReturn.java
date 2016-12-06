package com.sf.lottery.web.weixin.domain;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/3.
 */
public class JSApiTicketReturn extends BaseReturn {
    private String ticket;
    private int expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
