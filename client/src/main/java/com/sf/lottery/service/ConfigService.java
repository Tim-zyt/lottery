package com.sf.lottery.service;

/**
 * Created by 01139954 on 2016/12/8.
 */
public interface ConfigService {
    int closeShark();

    int openStartShark();

    Boolean isStartShark();

    boolean setCurrentAward(int awardId);

    boolean setCurrentOpera(int operaId);
}
