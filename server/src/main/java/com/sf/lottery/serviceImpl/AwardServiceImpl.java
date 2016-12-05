package com.sf.lottery.serviceImpl;

import com.sf.lottery.common.model.Award;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.manager.AwardManager;
import com.sf.lottery.service.AwardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/4
 */
@Service
public class AwardServiceImpl implements AwardService{
    private final static Logger log = LoggerFactory.getLogger(AwardServiceImpl.class);

    @Autowired AwardManager awardManager;

    @Override
    public boolean addAward(Award award) throws Exception{
        try{
            return awardManager.addAward(award);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean deleteAwardByAwardId(Integer awardId) throws Exception{
        try{
            return awardManager.deleteAwardByAwardId(awardId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public Award getAward(Integer awardId) throws Exception{
        try{
            return awardManager.getAward(awardId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean updateAward(Award award) throws Exception {
        try{
            return awardManager.updateAward(award);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<Award> getAwardByKind(String awKind) throws Exception{
        try{
            return awardManager.getAwardByKind(awKind);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public Set<String> getAllAwKinds() throws Exception {
        try{
            return awardManager.getAllAwKinds();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
