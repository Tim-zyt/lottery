package com.sf.lottery.serviceImpl;


import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.manager.ConfigManager;
import com.sf.lottery.manager.UserManager;
import com.sf.lottery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 01139954 on 2016/11/30.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserManager userManager;
    @Autowired
    ConfigManager configManager;

    @Override
    public int saveUser(User user) throws Exception {
        try {
            return userManager.updateByNoAndName(user);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<User> getSignedUser() throws Exception {
        try {
            return userManager.getSignedUser();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public User getUserById(int userId) throws Exception {
        try {
            return userManager.getUserById(userId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public User getUserBySfNumAndName(int sfNum, String sfName) throws Exception {
        try {
            return userManager.getUserBySfNumAndName(sfNum,sfName);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public Integer isSignedByWxInfo(String openId){
        return userManager.isSignedByWxInfo(openId);
    }

    @Override
    public boolean verifyUser(int sfNum,String sfName){
        int count = userManager.VerifyUser(sfNum,sfName);
        if(count == 1){
            return true;
        }
        return false;
    }

    @Override
    public int getSignedAmount() throws Exception{
        try {
            return userManager.getSignedAmount();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public int getTotalUserAmount() throws Exception {
        try {
            return userManager.getTotalUserAmount();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<User> getUnAwardUser() throws Exception {
        try {
            return userManager.getUnAwardUser();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<User> getAwardUser() throws Exception{
        try {
            return userManager.getAwardUser();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean resetUsers() throws Exception {
        try {
            return userManager.resetUsers();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean resetAwardUser() throws Exception {
        try {
            return userManager.resetAwardUser();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean resetConfig() throws Exception {
        try {
            return userManager.resetConfig();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean resetCouple() throws Exception {
        try {
            return userManager.resetCouple();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean deleteWinner(int userId) throws Exception{
        try{
        return userManager.deleteWinner(userId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean isSignedByUserNnm(int sfNum){
        try{
            return userManager.isSignedByUserNnm(sfNum);
        } catch (Exception e) {
            log.info("cp签到工号错误");
            return false;
        }
    }

    @Override
    public boolean cpSign(int sfNum1,int sfNum2,String imgSrc) throws Exception{
        return userManager.cpSign(sfNum1,sfNum2,imgSrc);
    }

    @Override
    public boolean isCanCpsign() throws Exception{
        return configManager.IsCanCpsign();
    }

    @Override
    public Boolean setUserShakeAward(Integer userId) throws Exception{
        return userManager.setUserShakeAward(userId);
    }
}