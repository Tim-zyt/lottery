package com.sf.lottery.serviceImpl;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.manager.OperaManager;
import com.sf.lottery.service.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/6.
 */
@Service
public class OperaServiceImpl implements OperaService{
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private OperaManager operaManager;

    @Override
    public int updateByPrimaryKey(Opera opera){
        return operaManager.updateByPrimaryKey(opera);
    }

    @Override
    public boolean updateOpera(Opera opera) throws Exception {
        try{
            return operaManager.updateOpera(opera);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean addOpera(Opera opera) throws Exception {
        try{
            return operaManager.addOpera(opera);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<Opera> getAllOperas() throws Exception {
        try{
            return operaManager.getAllOperas();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean deleteOpera(Integer operaId) throws Exception {
        try{
            return operaManager.deleteOpera(operaId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public Opera getOperaByOperaId(Integer operaId) throws Exception {
        try{
            return operaManager.getOperaByOperaId(operaId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
