package com.sf.lottery.serviceImpl;

import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.common.vo.CpGiftVo;
import com.sf.lottery.manager.CoupleManager;
import com.sf.lottery.service.CoupleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by 01139954 on 2016/12/11.
 */
@Service
public class CoupleServiceImpl implements CoupleService {
    private final static Logger log = LoggerFactory.getLogger(CoupleServiceImpl.class);

    @Autowired
    private CoupleManager coupleManager;

    @Override
    public List<CpGiftVo> getAllCouple() {
        return coupleManager.getAllCouple();
    }

    @Override
    public List<CpGiftVo> getAllUnAwardCouple() {
        return coupleManager.getAllUnAwardCouple();
    }

    @Override
    public CpGiftVo getLuckCP(){
        return coupleManager.getLuckCP();
    }

    @Override
    public boolean deleteCouple(int coupleId){
        return coupleManager.deleteCouple(coupleId);
    }

    @Override
    public int unConfirmCPImg(int coupleId){
        return coupleManager.unConfirmCPImg(coupleId);
    }

    @Override
    public int confirmCPImg(int coupleId){
        return coupleManager.confirmCPImg(coupleId);
    }

    @Override
    public boolean isCpSign(int userId){
        return coupleManager.isCpSign(userId);
    }

    @Override
    public boolean resetCouple() throws Exception {
        try {
            return coupleManager.resetCouple();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
