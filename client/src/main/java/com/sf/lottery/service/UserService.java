package com.sf.lottery.service;

import com.sf.lottery.common.model.User;

import java.util.List;

/**
 * Created by 01139954 on 2016/11/30.
 */
public interface UserService {
    /**
     * 保存用户信息，需要传入由工号、姓名、微信信息组合的对象，
     * 并且第一次保存时，设置该用户已签到，并返回用户主键
     * @return
     */
    int saveUser(User user) throws Exception;

    /**
     * 获得已签到的用户，返回对象只包含用户的ID、微信号、微信头像、工号、姓名、性别、签到时间
     * @return
     */
    List<User> getSignedUser() throws Exception;

    /**
     * 根据微信登录信息获取用户是否已签到
     * @return
     */
    boolean isSignedByWxInfo(String openId);

    /**
     * 判断该用户是否存在
     * @return
     */
    boolean verifyUser(int sfNum,String sfName);
    User getUserById(int userId) throws Exception;
}
