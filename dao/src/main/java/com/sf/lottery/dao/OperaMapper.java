package com.sf.lottery.dao;

import com.sf.lottery.common.model.Opera;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/6
 */
public interface OperaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Opera record);

    int insertSelective(Opera record);

    Opera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Opera record);

    int updateByPrimaryKey(Opera record);
}