package com.sf.lottery.web.gift;

import java.io.Serializable;

/**
 * Created by 01139954 on 2016/12/8.
 */
public class UserShakeVo implements Serializable,Comparable {
    //ID
    private Integer userId;

    //头像
    private String headImgUrl;

    //用户真实姓名
    private String userName;

    //工号
    private Integer userNo;

    //摇的次数
    private Integer shakeCount;

    @Override
    public int compareTo(Object o) {
        if(this == o){
            return 0;
        }
        else if (o != null && o instanceof UserShakeVo) {
            UserShakeVo u = (UserShakeVo) o;
            if(this.shakeCount < u.shakeCount){
                return -1;
            }else if(this.shakeCount > u.shakeCount){
                return 1;
            }else {
                return 0;
            }
        }else{
            return 1;
        }
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getShakeCount() {
        return shakeCount;
    }

    public void setShakeCount(Integer shakeCount) {
        this.shakeCount = shakeCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
