package com.sf.lottery.manager;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.dao.OperaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/6.
 */
@Component
public class OperaManager {
    @Autowired
    private OperaMapper operaMapper;

    public int updateByPrimaryKey(Opera record){
        return operaMapper.updateByPrimaryKey(record);
    }
}
