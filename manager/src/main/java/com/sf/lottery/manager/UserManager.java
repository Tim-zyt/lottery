package com.sf.lottery.manager;

import com.sf.lottery.common.model.*;
import com.sf.lottery.common.utils.RandomUtil;
import com.sf.lottery.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger log = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private AwardUserMapper awardUserMapper;

    @Autowired
    private CoupleMapper coupleMapper;

    @Autowired
    private RandomUtil randomUtil;

    public boolean updateByNoAndName(User user){
        if(userMapper.updateByNoAndName(user)>0){
            return true;
        }else
            return false;
    }

    public List<User> getSignedUser(){
        return userMapper.selectSignedUser();
    }

    public Integer isSignedByWxInfo(String openId){
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
        List<User> awardUser = new LinkedList<>();
        //获取当前奖品
        int id = 0;
        Config config = configMapper.selectByIsOpen();
        if(config != null){
            id = config.getCurGiftId();
        }
        Award award = awardMapper.selectByPrimaryKey(id);
        if(award == null){
            log.info("当前奖品未设置");
            return awardUser;
        }

        List<User> unAwardUser =  userMapper.getUnAwardUser();
        if(unAwardUser == null || unAwardUser.size() == 0){
            log.info("获取未获奖人数出错");
            return awardUser;
        }
        if(award.getAwUserCount() >= unAwardUser.size()){
            //如果奖品人数比未获奖人数还多，那就所有人中奖。
            saveAwardUserRecord(unAwardUser , award);
            return unAwardUser;
        }
        int[] luckIndex = RandomUtil.getNRandom(0 , unAwardUser.size() , award.getAwUserCount());
        int iLen = luckIndex.length;
        for(int i = 0 ; i < iLen ; i++){
            awardUser.add(unAwardUser.get(luckIndex[i]));
        }
        saveAwardUserRecord(awardUser , award);
        return awardUser;
    }

    //将获奖人持久化到数据库
    private void saveAwardUserRecord( List<User> awardUser , Award award){
        //将awardUser持久化到数据库的关联表
        List<AwardUser> awardUsersRele = new LinkedList<>();
        int jLen = awardUser.size();
        for(int j = 0 ; j < jLen ; j++){
            AwardUser awardUserTe = new AwardUser();
            awardUserTe.setUserId(awardUser.get(j).getId());
            awardUserTe.setAwardId(award.getId());
            awardUsersRele.add(awardUserTe);
        }
        awardUserMapper.insertBatch(awardUsersRele);

        //将用户表的award_count设为1
        userMapper.updateAwardCountBatch(awardUser);
    }

    public User getUserBySfNumAndName(int sfNum, String sfName) {
        return userMapper.selectUserByUniqueKey(sfNum,sfName);
    }

    public boolean resetUsers(){
        boolean b = userMapper.resetUsers()>0;
        return b;
    }

    public boolean resetAwardUser(){
        boolean b = awardUserMapper.resetAwardUser()>0;
        return b;
    }


    //删除获奖-用户临时表（未到现场）
    public boolean deleteWinner(int userId){
        if(awardUserMapper.deleteByUserId(userId)>0){
            return true;
        }else
            return false;
    }

    //根据工号判断用户是否签到
    public boolean isSignedByUserNnm(int sfNum) throws Exception{
        if(userMapper.isSignedByUserNum(sfNum) == true){
            return true;
        }else
            return false;
    }

    //cp签到
    public boolean cpSign(int sfNum1,int sfNum2,String imgSrc){
        if((coupleMapper.isCpSignedByUserNum(sfNum1)==0)&&(coupleMapper.isCpSignedByUserNum(sfNum2)==0)){
            Couple couple = new Couple();
            couple.setUser1SfNum(sfNum1);
            couple.setUser2SfNum(sfNum2);
            couple.setCpImg(imgSrc);
            int isSuccess = coupleMapper.insertSelective(couple);
            if(isSuccess==1){
                return true;
            }else
                return false;
        }else{
            return false;
        }
    }

    /**
     * 设置某人中摇一摇奖品
     * @return
     */
    public Boolean setUserShakeAward(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user != null){
            user.setAwardCount(1);
            List<Award> awards = awardMapper.selectAwardByKind("摇一摇");
            if(awards.size() > 0){
                Award award = awards.get(0);
                List<AwardUser> awardUsersRele = new LinkedList<>();
                AwardUser awardUserTe = new AwardUser();
                awardUserTe.setUserId(userId);
                awardUserTe.setAwardId(award.getId());
                awardUsersRele.add(awardUserTe);
                return (awardUserMapper.insertBatch(awardUsersRele) > 0 && userMapper.updateByPrimaryKey(user) > 0);
            }
        }
        return false;
    }
}
