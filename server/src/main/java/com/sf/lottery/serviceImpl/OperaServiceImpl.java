package com.sf.lottery.serviceImpl;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.manager.OperaManager;
import com.sf.lottery.service.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
