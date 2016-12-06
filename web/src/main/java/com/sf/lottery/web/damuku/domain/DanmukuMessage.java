package com.sf.lottery.web.damuku.domain;

import java.io.Serializable;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/1.
 */
public class DanmukuMessage implements Serializable {
    private String sfUserNum;
    private String sfUserName;
    private String wxAvatar;
    private int type; //0为弹幕，1为鲜花，2为汽车，3为火箭
    private String content;

    public String getSfUserNum() {
        return sfUserNum;
    }

    public void setSfUserNum(String sfUserNum) {
        this.sfUserNum = sfUserNum;
    }

    public String getSfUserName() {
        return sfUserName;
    }

    public void setSfUserName(String sfUserName) {
        this.sfUserName = sfUserName;
    }

    public String getWxAvatar() {
        return wxAvatar;
    }

    public void setWxAvatar(String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
