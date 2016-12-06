package com.sf.lottery.dao;

import com.sf.lottery.common.model.awardUser;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/6
 */
public interface awardUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(awardUser record);

    int insertSelective(awardUser record);

    awardUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(awardUser record);

    int updateByPrimaryKey(awardUser record);
}