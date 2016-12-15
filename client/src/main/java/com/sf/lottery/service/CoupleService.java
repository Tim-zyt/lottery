package com.sf.lottery.service;

import com.sf.lottery.common.vo.CpGiftVo;

import java.util.List;

/**
 * Created by 01139954 on 2016/12/11.
 */
public interface CoupleService {
    List<CpGiftVo> getAllCouple();

    List<CpGiftVo> getAllUnAwardCouple();

    CpGiftVo getLuckCP();

    boolean deleteCouple(int coupleId);

    boolean isCpSign(int sfNum);
}
