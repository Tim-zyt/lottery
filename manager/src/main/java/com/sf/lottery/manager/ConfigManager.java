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

    //是否开始摇一摇
    public Boolean isStartShark(){
        Config config = configMapper.selectByIsOpen();
        if(config == null || config.getIsStartShake() == null){
            return false;
        }
        return config.getIsStartShake();
    }

    //开启摇一摇
    public int openStartShark(){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setIsStartShake(true);
            config.setIsOpen(true);
            return configMapper.insert(config);
        }
        config.setIsStartShake(true);
        config.setIsOpen(true);
        return configMapper.updateByIsOpen(config);
    }

    //关闭摇一摇
    public int closeShark(){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setIsStartShake(false);
            config.setIsOpen(true);
            return configMapper.insert(config);
        }
        config.setIsStartShake(false);
        config.setIsOpen(true);
        return configMapper.updateByIsOpen(config);
    }

    //设置当前礼品
    public boolean setCurrentAward(int awardId){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setCurGiftId(awardId);
        }
        config.setCurGiftId(awardId);
        int result = configMapper.updateByIsOpen(config);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    //设置当前节目
    public boolean setCurrentOpera(int operaId){
        Config config = configMapper.selectByIsOpen();
        if(config == null){
            config = new Config();
            config.setCurProgramId(operaId);
        }
        config.setCurProgramId(operaId);
        int result = configMapper.updateByIsOpen(config);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }
}
