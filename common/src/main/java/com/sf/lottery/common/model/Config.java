package com.sf.lottery.common.model;

import java.io.Serializable;

/**
 * Created by 01139954 on 2016/12/6.
 */
public class Config implements Serializable {
    private Integer id;

    private Integer curGiftId;

    private Integer curProgramId;

    private Boolean isGift;

    private Boolean isOpen;

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

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }
}
