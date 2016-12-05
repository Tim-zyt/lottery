package com.sf.lottery.common.model;

import java.io.Serializable;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/4
 */
public class Award implements Serializable {
    private Integer id;

    private String awName;

    private String awDescription;

    private String awImg;

    private Integer awUserCount;

    private String awKind;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    //public void setId(Integer id) {
    //    this.id = id;
    //}

    public String getAwName() {
        return awName;
    }

    public void setAwName(String awName) {
        this.awName = awName == null ? null : awName.trim();
    }

    public String getAwDescription() {
        return awDescription;
    }

    public void setAwDescription(String awDescription) {
        this.awDescription = awDescription == null ? null : awDescription.trim();
    }

    public String getAwImg() {
        return awImg;
    }

    public void setAwImg(String awImg) {
        this.awImg = awImg == null ? null : awImg.trim();
    }

    public Integer getAwUserCount() {
        return awUserCount;
    }

    public void setAwUserCount(Integer awUserCount) {
        this.awUserCount = awUserCount;
    }

    public String getAwKind() {
        return awKind;
    }

    public void setAwKind(String awKind) {
        this.awKind = awKind == null ? null : awKind.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", awName=").append(awName);
        sb.append(", awDescription=").append(awDescription);
        sb.append(", awImg=").append(awImg);
        sb.append(", awUserCount=").append(awUserCount);
        sb.append(", awKind=").append(awKind);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}