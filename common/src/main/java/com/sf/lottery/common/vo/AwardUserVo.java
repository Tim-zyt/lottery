package com.sf.lottery.common.vo;

import java.io.Serializable;

/**
 * Created by 01139954 on 2016/12/12.
 */
public class AwardUserVo implements Serializable {
    private Integer id;

    private String awName;

    private Integer sfNum;

    private String sfName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAwName() {
        return awName;
    }

    public void setAwName(String awName) {
        this.awName = awName;
    }

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
}
