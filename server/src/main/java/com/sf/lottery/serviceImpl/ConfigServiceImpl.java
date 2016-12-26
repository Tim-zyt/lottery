package com.sf.lottery.serviceImpl;

import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.manager.ConfigManager;
import com.sf.lottery.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 01139954 on 2016/12/8.
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    private final static Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigManager configManager;

    @Override
    public boolean setCurrentAward(int awardId) {
        return configManager.setCurrentAward(awardId);
    }

    @Override
    public boolean setCurrentOpera(int operaId) {
        return configManager.setCurrentOpera(operaId);
    }

    @Override
    public Boolean isCanShake() {
        return configManager.isCanShake();
    }

    @Override
    public int closeShake() {
        return configManager.closeShake();
    }

    @Override
    public int openShake() {
        return configManager.openShake();
    }

    @Override
    public Boolean isCanReward() {
        return configManager.isCanReward();
    }

    @Override
    public int closeReward() {
        return configManager.closeReward();
    }

    @Override
    public int openReward() {
        return configManager.openReward();
    }

    @Override
    public Boolean IsCanCpsign() {
        return configManager.IsCanCpsign();
    }

    @Override
    public int closeCpsign() {
        return configManager.closeCpsign();
    }

    @Override
    public int openCpsign() {
        return configManager.openCpsign();
    }

    @Override
    public int selectCurProgramId(){
        return configManager.selectCurProgramId();
    }

    @Override
    public int selectCurGiftId(){
        return configManager.selectCurGiftId();
    }

    @Override
    public boolean resetConfig() throws Exception {
        try {
            return configManager.resetConfig();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
     public int getCurStateAward() throws Exception{
        return configManager.getCurStateAward();
    }

    @Override
    public int getCurStateCp() throws Exception{
        return configManager.getCurStateCp();
    }

    @Override
    public int getCurStateShake() throws Exception{
        return configManager.getCurStateShake();
    }

    @Override
    public int setCurStateAward(int curStateAward) throws Exception{
        return configManager.setCurStateAward(curStateAward);
    }

    @Override
    public int setCurStateCp(int curStateCp) throws Exception{
        return configManager.setCurStateCp(curStateCp);
    }

    @Override
    public int setCurStateShake(int curStateShake) throws Exception{
        return configManager.setCurStateShake(curStateShake);
    }

    @Override
    public int getCurSfPay() throws Exception{
         return configManager.getCurSfPay();
    }

    @Override
    public boolean setCurSfPay(int curSfPay) throws Exception{
        return configManager.setCurSfPay(curSfPay);
    }


}
