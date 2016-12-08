package com.sf.lottery.serviceImpl;

import com.sf.lottery.manager.ConfigManager;
import com.sf.lottery.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 01139954 on 2016/12/8.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigManager configManager;

    @Override
    public int closeShark() {
        return configManager.closeShark();
    }

    @Override
    public int openStartShark() {
        return configManager.openStartShark();
    }

    @Override
    public Boolean isStartShark() {
        return configManager.isStartShark();
    }

    @Override
    public boolean setCurrentAward(int awardId){
        return configManager.setCurrentAward(awardId);
    }

    @Override
    public boolean setCurrentOpera(int operaId){
        return configManager.setCurrentOpera(operaId);
    }
}
