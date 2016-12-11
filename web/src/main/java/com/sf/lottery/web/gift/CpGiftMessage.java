package com.sf.lottery.web.gift;

import com.sf.lottery.common.model.Couple;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.vo.CpGiftVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01139954 on 2016/12/6.
 */
public class CpGiftMessage implements Serializable {
    /**
     * 0:表示当前是抽人，前端页面头像开始闪烁；
     * 1:表示当前页面是展示获奖人头像
     */
    private int flag;

    /**
     * 当flag为0时，luckmans为空；
     * 当flag为1时，表示获奖的人；
     */
    private CpGiftVo luckCP;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public CpGiftVo getLuckCP() {
        return luckCP;
    }

    public void setLuckCP(CpGiftVo luckCP) {
        this.luckCP = luckCP;
    }
}
