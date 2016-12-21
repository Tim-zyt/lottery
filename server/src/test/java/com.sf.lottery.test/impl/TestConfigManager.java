package com.sf.lottery.test.impl;

import com.sf.lottery.manager.ConfigManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 01139954 on 2016/12/10.
 */
public class TestConfigManager extends BaseTest {
    @Autowired
    private ConfigManager configManager;


    @Test
    public void testIsCanCpsign(){
        Assert.assertEquals(false,configManager.IsCanCpsign());
    }

    @Test
    public void testIsCanReward(){
        Assert.assertEquals(true,configManager.isCanReward());
    }

    @Test
    public void testIsCanShark(){
        Assert.assertEquals(false,configManager.isCanShake());
    }

    @Test
    public void testCloseCpsign(){
        Assert.assertEquals(true,configManager.closeCpsign() > 0);
    }
    @Test
    public void testCloseReward(){
        Assert.assertEquals(true,configManager.closeReward() > 0);
    }
    @Test
    public void testCloseShark(){
        Assert.assertEquals(true,configManager.closeShake() > 0);
    }
    @Test
    public void testOpenCpsign(){
        Assert.assertEquals(true,configManager.openCpsign() > 0);
    }

    @Test
    public void testOpenReward(){
        Assert.assertEquals(true,configManager.openReward() > 0);
    }

    @Test
    public void testOpenShark(){
        Assert.assertEquals(true,configManager.openShake() > 0);
    }

    @Test
    public void testgetCurStateAward(){
        Assert.assertEquals(0,configManager.getCurStateAward());
    }

    @Test
    public void testgetCurStateCp(){
        Assert.assertEquals(0,configManager.getCurStateCp());
    }

    @Test
    public void testgetCurStateShake(){
        Assert.assertEquals(0,configManager.getCurStateShake());
    }

    @Test
    public void testsetCurStateAward(){
        Assert.assertEquals(1,configManager.setCurStateAward(1));
    }

    @Test
    public void testsetCurStateCp(){
        Assert.assertEquals(1,configManager.setCurStateCp(1));
    }

    @Test
    public void testsetCurStateShake(){
        Assert.assertEquals(1,configManager.setCurStateShake(1));
    }
}
