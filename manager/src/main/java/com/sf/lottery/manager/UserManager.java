package com.sf.lottery.manager;

import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.RandomUtil;
import com.sf.lottery.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
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

    @Autowired
    private RandomUtil randomUtil;

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

    public int getSignedAmount(){
        return userMapper.getSignedAmount();
    }

    public int getTotalUserAmount(){
        return userMapper.getTotalUserAmount();
    }

    public List<User> getUnAwardUser(){
        return userMapper.getUnAwardUser();
    }

    public List<User> getAwardUser(){
        List<User> unAwardUser =  userMapper.getUnAwardUser();
        List<User> awardUser = new LinkedList<>();
        int[] luckIndex = RandomUtil.getNRandom(0 , unAwardUser.size() , 5);
        int iLen = luckIndex.length;
        for(int i = 0 ; i < iLen ; i++){
            awardUser.add(unAwardUser.get(luckIndex[i]));
        }
        //todo 将awardUser持久化到数据库
        return awardUser;
    }
}
