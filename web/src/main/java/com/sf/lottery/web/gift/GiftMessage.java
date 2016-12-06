package com.sf.lottery.web.gift;

import com.sf.lottery.common.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01139954 on 2016/12/6.
 */
public class GiftMessage implements Serializable {
    /**
     * 0:表示当前是抽人，前端页面头像开始闪烁；
     * 1:表示当前页面是展示获奖人头像
     */
    private int flag;

    /**
     * 当flag为0时，表示头像闪烁的个数；
     * 当flag为1时，表示获奖的人数
     */
    private int luckmanCount;

    /**
     * 当flag为0时，luckmans为空；
     * 当flag为1时，表示获奖的人；
     */
    private List<User> luckmans = new ArrayList<>();

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getLuckmanCount() {
        return luckmanCount;
    }

    public void setLuckmanCount(int luckmanCount) {
        this.luckmanCount = luckmanCount;
    }

    public List<User> getLuckmans() {
        return luckmans;
    }

    public void setLuckmans(List<User> luckmans) {
        this.luckmans = luckmans;
    }
}
