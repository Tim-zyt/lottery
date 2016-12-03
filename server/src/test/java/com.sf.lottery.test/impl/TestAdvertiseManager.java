package com.sf.lottery.test.impl;

import com.sf.lottery.manager.UserManager;
import com.sf.lottery.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 01139940 on 2016/11/28.
 */
public class TestAdvertiseManager extends BaseTest {
    @Autowired
    private UserManager userManager;

    @Test
    public void testisSignedByWxInfo(){
        Assert.assertEquals(1,userManager.isSignedByWxInfo("1"));

    }
}
