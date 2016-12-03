package com.sf.lottery.manager;

import com.sf.lottery.common.model.User;
import com.sf.lottery.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/11/30
 */
@Component
public class UserManager {

    @Autowired
    private UserMapper userMapper;


    public int updateByNoAndName(User user){
        return userMapper.updateByNoAndName(user);
    }

    public List<User> getSignedUser(){
        return userMapper.selectSignedUser();
    }

    public int isSignedByWxInfo(String openId){
        return userMapper.isSignedByWxInfo(openId);
    }

    public int VerifyUser(int sfNum,String sfName){
        return userMapper.verifyUser(sfNum,sfName);
    }

    public User getUserById(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }
}
