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
        Assert.assertEquals(false,configManager.isCanShark());
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
        Assert.assertEquals(true,configManager.closeShark() > 0);
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
        Assert.assertEquals(true,configManager.openShark() > 0);
    }


}
