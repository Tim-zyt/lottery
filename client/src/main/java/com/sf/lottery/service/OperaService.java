package com.sf.lottery.service;

import com.sf.lottery.common.model.Opera;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/6.
 */
public interface OperaService {
    /**
     * 刷新节目打赏个数
     * @param opera
     * @return
     */
    int updateByPrimaryKey(Opera opera) throws Exception;
}
