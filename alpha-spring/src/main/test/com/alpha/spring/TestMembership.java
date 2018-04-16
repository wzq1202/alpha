package com.alpha.spring;

import com.alpha.spring.biz.ProductStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ssports on 2018/4/16.
 */
@SpringBootTest(classes = AlphaSpringApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMembership {
    @Autowired
    private MembershipRegistry membershipRegistry;
    @Test
    public void testMemberhsip(){
        membershipRegistry.notify(ProductStore.getProduct("5401"));
    }
}
