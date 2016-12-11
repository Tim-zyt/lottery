package com.sf.lottery.manager;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.dao.OperaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public boolean addOpera(Opera opera){
        boolean b = operaMapper.insertSelective(opera)>0;
        return b;
    }

    public boolean deleteOpera(Integer operaId){
        boolean b = operaMapper.deleteByPrimaryKey(operaId)>0;
        return b;
    }

    public Opera getOperaByOperaId(Integer operaId){
        return operaMapper.selectByPrimaryKey(operaId);
    }

    public List<Opera> getAllOperas(){
        return operaMapper.selectAllOperas();
    }

    public boolean updateOpera(Opera opera){
        boolean b = operaMapper.updateByPrimaryKeySelective(opera) > 0;
        return b;
    }
}
