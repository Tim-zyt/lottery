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

    int selectCurProgramId();

    int selectCurGiftId();

    int resetConfig();

    int getCurStateAward();

    int getCurStateCp();

    int getCurStateShake();

    int setCurStateAward(int curStateAward);

    int setCurStateCp(int curStateCp);

    int setCurStateShake(int curStateShake);
}
