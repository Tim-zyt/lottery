package com.sf.lottery.client.user.service;

import java.util.List;

/**
 * Created by 01139954 on 2016/11/30.
 */
public interface UserService {
    /**
     * 保存用户信息，需要传入由工号、姓名、微信信息组合的对象，
     * 并且第一次保存时，设置该用户已签到，并由代码生成签到时间
     * @return
     */
    boolean saveUser();

    /**
     * 获得已签到的用户，返回对象只包含用户的ID、微信号、微信头像、工号、姓名、性别、签到时间
     * @return
     */
    List<Object> getIsSignUser();

}
