package com.sf.lottery.common.model;

import java.util.Date;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/11/30
 */
public class SignedUser {
    private Integer id;

    private Integer sfNum;

    private String sfName;

    private Integer wxSex;

    private Date signedTime;

    private String wxOpenid;

    private String wxHeadimgurl;

    public Integer getId() {
        return id;
    }

    //public void setId(Integer id) {
    //    this.id = id;
    //}

    public Integer getSfNum() {
        return sfNum;
    }

    public void setSfNum(Integer sfNum) {
        this.sfNum = sfNum;
    }

    public String getSfName() {
        return sfName;
    }

    public void setSfName(String sfName) {
        this.sfName = sfName;
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public Date getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getWxHeadimgurl() {
        return wxHeadimgurl;
    }

    public void setWxHeadimgurl(String wxHeadimgurl) {
        this.wxHeadimgurl = wxHeadimgurl;
    }
}
