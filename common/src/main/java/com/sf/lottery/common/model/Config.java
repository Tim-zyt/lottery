package com.sf.lottery.common.model;

import java.io.Serializable;

/**
 * Created by 01139954 on 2016/12/6.
 */
public class Config implements Serializable {
    private Integer id;

    private Integer curGiftId;

    private Integer curProgramId;

    //是否开启抽奖
    private Boolean isCanReward;

    //是否开始摇一摇
    private Boolean isCanShake;

    //是否可以CP签到
    private Boolean isCanCpsign;

    private Boolean isOpen;

    private Integer curStateAward;

    private Integer curStateCp;

    private Integer curStateShake;

    private Integer curSfPay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurGiftId() {
        return curGiftId;
    }

    public void setCurGiftId(Integer curGiftId) {
        this.curGiftId = curGiftId;
    }

    public Integer getCurProgramId() {
        return curProgramId;
    }

    public void setCurProgramId(Integer curProgramId) {
        this.curProgramId = curProgramId;
    }

    public Boolean getIsCanReward() {
        return isCanReward;
    }

    public void setIsCanReward(Boolean isCanReward) {
        this.isCanReward = isCanReward;
    }

    public Boolean getIsCanShake() {
        return isCanShake;
    }

    public void setIsCanShake(Boolean isCanShake) {
        this.isCanShake = isCanShake;
    }

    public Boolean getIsCanCpsign() {
        return isCanCpsign;
    }

    public void setIsCanCpsign(Boolean isCanCpsign) {
        this.isCanCpsign = isCanCpsign;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getCurStateAward() {
        return curStateAward;
    }

    public void setCurStateAward(Integer curStateAward) {
        this.curStateAward = curStateAward;
    }

    public Integer getCurStateCp() {
        return curStateCp;
    }

    public void setCurStateCp(Integer curStateCp) {
        this.curStateCp = curStateCp;
    }

    public Integer getCurStateShake() {
        return curStateShake;
    }

    public void setCurStateShake(Integer curStateShake) {
        this.curStateShake = curStateShake;
    }

    public Integer getCurSfPay() {
        return curSfPay;
    }

    public void setCurSfPay(Integer curSfPay) {
        this.curSfPay = curSfPay;
    }
}
