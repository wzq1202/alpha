package com.alpha.spring.biz;

import com.alpha.spring.CompentLoader;
import com.alpha.spring.annotation.Membership;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "999",productId = "111")
public class VipMemership implements MembershipHandler,Commitable {
    @Autowired
    private CompentLoader compentLoader;
    @Override
    public Object process(Map<String, String> map) {
        System.out.println("compentloader ====== " + compentLoader);
        return null;
    }

    @Override
    public boolean commit() {
        return false;
    }
}
