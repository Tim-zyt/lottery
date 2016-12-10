package com.sf.lottery.test.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 01139954 on 2016/12/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-service.xml" })
public class BaseTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Before
    public void init(){
    }
    @After
    public void print(){
    }
}