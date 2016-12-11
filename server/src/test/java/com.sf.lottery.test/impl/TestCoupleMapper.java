package com.sf.lottery.test.impl;

import com.sf.lottery.common.vo.CpGiftVo;
import com.sf.lottery.dao.CoupleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 01139954 on 2016/12/10.
 */
public class TestCoupleMapper extends BaseTest {
    @Autowired
    private CoupleMapper coupleMapper;


    @Test
    public void testIsCanCpsign(){
        List<CpGiftVo> cpGiftVo = coupleMapper.selectAllCouple();
    }

    @Test
    public void testRo(){
        for(int i = 0 ; i < 100 ; i++){
            int luckIndex = (int) (Math.random() * 1);
            System.out.print(luckIndex + ",");
        }

    }


}
