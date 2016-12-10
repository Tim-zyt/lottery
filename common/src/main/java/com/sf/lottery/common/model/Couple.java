package com.sf.lottery.common.model;

import java.io.Serializable;

public class Couple implements Serializable {
    private Integer id;

    private Integer user1SfNum;

    private Integer user2SfNum;

    private String cpImg;

    private Integer awCount;

    private Integer awId;

    private static final long serialVersionUID = 1L;

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
        this.cpImg = cpImg == null ? null : cpImg.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user1SfNum=").append(user1SfNum);
        sb.append(", user2SfNum=").append(user2SfNum);
        sb.append(", cpImg=").append(cpImg);
        sb.append(", awCount=").append(awCount);
        sb.append(", awId=").append(awId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}