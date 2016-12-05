package com.sf.lottery.manager;

import com.sf.lottery.common.model.Award;
import com.sf.lottery.dao.AwardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/4
 */
@Component
public class AwardManager {
    @Autowired
    private AwardMapper awardMapper;

    public boolean deleteAwardByAwardId(Integer awardId){
        boolean b = awardMapper.deleteByPrimaryKey(awardId)>0;
        return b;
    }

    public boolean addAward(Award award){
        boolean b = awardMapper.insertSelective(award)>0;
        return b;
    }

    public Award getAward(Integer awardId){
        return awardMapper.selectByPrimaryKey(awardId);
    }

    public boolean updateAward(Award record){
        boolean b = awardMapper.updateByPrimaryKeySelective(record)>0;
        return b;
    }

    public List<Award> getAwardByKind(String awKind){
        return awardMapper.selectAwardByKind(awKind);
    }

    public Set<String> getAllAwKinds(){
        return awardMapper.selectAllKind();
    }
}
