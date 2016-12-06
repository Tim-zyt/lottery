package com.sf.lottery.dao.AwardUser;

import com.sf.lottery.common.model.AwardUser;

import java.util.List;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/6
 */
public interface AwardUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardUser record);

    int insertSelective(AwardUser record);

    AwardUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardUser record);

    int updateByPrimaryKey(AwardUser record);

    int insertBatch(List<AwardUser> list);

}