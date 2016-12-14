package com.sf.lottery.service;

/**
 * Created by 01139954 on 2016/12/8.
 */
public interface ConfigService {

    boolean setCurrentAward(int awardId);

    boolean setCurrentOpera(int operaId);

    Boolean isCanShake();

    int closeShake();

    int openShake();

    Boolean isCanReward();

    int closeReward();

    int openReward();

    Boolean IsCanCpsign();

    int closeCpsign();

    int openCpsign();

    int selectCurProgromId();



}
