package com.sf.lottery.dao;

import com.sf.lottery.common.model.Config;

/**
 * Created by 01139954 on 2016/12/6.
 */
public interface ConfigMapper {
    int insert(Config record);

    int updateByIsOpen(Config record);

    Config selectByIsOpen();

    Config selectByPrimaryKey(int id);

    int selectCurProgromId();
}
