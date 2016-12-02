package com.sf.lottery.web.damuku.domain;

import java.io.Serializable;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/1.
 */
public class DanmukuMessage implements Serializable {
    private String sfNo;
    private String sfName;
    private String avatar;
    private int type; //0为弹幕，1为鲜花，2为汽车，3为游艇，4为火箭
    private String content;

    public String getSfNo() {
        return sfNo;
    }

    public void setSfNo(String sfNo) {
        this.sfNo = sfNo;
    }

    public String getSfName() {
        return sfName;
    }

    public void setSfName(String sfName) {
        this.sfName = sfName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
