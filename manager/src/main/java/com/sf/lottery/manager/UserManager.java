package com.sf.lottery.manager;

import com.sf.lottery.common.model.User;
import com.sf.lottery.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/11/30
 */
public class UserManager {
    @Autowired
    private UserMapper userMapper;

    /**
     * 如果签到成功，则设置签到成功
     * @param user
     * @return
     */
    public boolean addUser(User user){
        boolean b = userMapper.insertSelective(user)>0;
        if(b){
            user.setIsSign(true);
        }
        return b;
    }

    public List<User> getSignedUser(){
        return userMapper.selectSignedUser();
    }

    public User getUserById(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }
}
