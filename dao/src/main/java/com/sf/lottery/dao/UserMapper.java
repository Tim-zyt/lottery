package com.sf.lottery.dao;

import com.sf.lottery.common.model.User;

import java.util.List;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/11/30
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectSignedUser();

    int isSignedByWxInfo(String openId);

    int verifyUser(int sfNum,String sfName);

    int updateByNoAndName(User user);

    int getSignedAmount();

    int getTotalUserAmount();

    List<User> getUnAwardUser();
}