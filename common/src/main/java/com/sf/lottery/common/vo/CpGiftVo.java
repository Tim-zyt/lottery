package com.sf.lottery.common.vo;

import java.io.Serializable;

/**
 * Created by 01139954 on 2016/12/11.
 *  CP抽奖需要的值对象
 */
public class CpGiftVo implements Serializable {
    private Integer id;

    private Integer user1SfNum;

    private Integer user2SfNum;

    private String cpImg;

    private Integer awCount;

    private Integer awId;

    private String user1SfName;

    private String user2SfName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1SfNum() {
        return user1SfNum;
    }

    public void setUser1SfNum(Integer user1SfNum) {
        this.user1SfNum = user1SfNum;
    }

    public Integer getUser2SfNum() {
        return user2SfNum;
    }

    public void setUser2SfNum(Integer user2SfNum) {
        this.user2SfNum = user2SfNum;
    }

    public String getCpImg() {
        return cpImg;
    }

    public void setCpImg(String cpImg) {
        this.cpImg = cpImg;
    }

    public Integer getAwCount() {
        return awCount;
    }

    public void setAwCount(Integer awCount) {
        this.awCount = awCount;
    }

    public Integer getAwId() {
        return awId;
    }

    public void setAwId(Integer awId) {
        this.awId = awId;
    }

    public String getUser1SfName() {
        return user1SfName;
    }

    public void setUser1SfName(String user1SfName) {
        this.user1SfName = user1SfName;
    }

    public String getUser2SfName() {
        return user2SfName;
    }

    public void setUser2SfName(String user2SfName) {
        this.user2SfName = user2SfName;
    }
}