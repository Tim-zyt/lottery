package com.sf.lottery.manager;

import com.sf.lottery.common.model.Config;
import com.sf.lottery.dao.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 01139954 on 2016/12/7.
 */
@Component
public class ConfigManager {
    @Autowired
    private ConfigMapper configMapper;

    //设置当前礼品
    public Boolean setCurrentAward(int awardId){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setCurGiftId(awardId);
            config.setIsOpen(true);
            return configMapper.insert(config) > 0;
        }
        config.setCurGiftId(awardId);
        return configMapper.updateByIsOpen(config) > 0;
    }

    //设置当前节目
    public Boolean setCurrentOpera(int operaId){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setCurProgramId(operaId);
            config.setIsOpen(true);
            return configMapper.insert(config) > 0;
        }
        config.setCurProgramId(operaId);
        return configMapper.updateByIsOpen(config) > 0;
    }

    //是否开始摇一摇
    public Boolean isCanShake(){
        Config config = configMapper.selectByIsOpen();
        if(config == null || config.getIsCanShake() == null){
            return false;
        }
        return config.getIsCanShake();
    }

    //开启摇一摇
    public int openShake(){
        return changeIsCanShakeState(true);
    }

    //关闭摇一摇
    public int closeShake(){
        return changeIsCanShakeState(false);
    }

    private int changeIsCanShakeState(Boolean state){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setIsCanShake(state);
            config.setIsOpen(true);
            return configMapper.insert(config);
        }
        config.setIsCanShake(state);
        return configMapper.updateByIsOpen(config);
    }



    //是否可以打赏
    public Boolean isCanReward(){
        Config config = configMapper.selectByIsOpen();
        if(config == null || config.getIsCanReward() == null){
           return false;
        }
        return config.getIsCanReward();
    }

    //开启打赏
    public int openReward(){
        return changeIsCanRewardState(true);
    }

    //关闭打赏
    public int closeReward(){
        return changeIsCanRewardState(false);
    }

    private int changeIsCanRewardState(Boolean state){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setIsCanReward(state);
            config.setIsOpen(true);
            return configMapper.insert(config);
        }
        config.setIsCanReward(state);
        return configMapper.updateByIsOpen(config);
    }


    //是否可以CP签到
    public Boolean IsCanCpsign(){
        Config config = configMapper.selectByIsOpen();
        if(config == null || config.getIsCanCpsign() == null){
            return false;
        }
        return config.getIsCanCpsign();
    }

    //开启CP签到
    public int openCpsign(){
        return changeIsCanCpsignState(true);
    }

    //关闭CP签到
    public int closeCpsign(){
        return changeIsCanCpsignState(false);
    }

    private int changeIsCanCpsignState(Boolean state){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setIsCanCpsign(state);
            config.setIsOpen(true);
            return configMapper.insert(config);
        }
        config.setIsCanCpsign(state);
        return configMapper.updateByIsOpen(config);
    }
}
