package com.sf.lottery.service;

import com.sf.lottery.common.model.Opera;

import java.util.List;

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

    /**
     * 更新节目
     * @param opera
     * @return
     * @throws Exception
     */
    boolean updateOpera(Opera opera) throws Exception;

    /**
     * 添加节目
     * @param opera
     * @return
     * @throws Exception
     */
    boolean addOpera(Opera opera) throws Exception;

    /**
     * 得到节目列表
     * @return
     * @throws Exception
     */
    List<Opera> getAllOperas() throws Exception;

    /**
     * 删除节目
     * @param operaId
     * @return
     * @throws Exception
     */
    boolean deleteOpera(Integer operaId) throws Exception;

    /**
     * 根据节目id得到节目
     * @param operaId
     * @return
     * @throws Exception
     */
    Opera getOperaByOperaId(Integer operaId) throws Exception;
}
