package com.alpha.spring.biz;

import com.alpha.spring.annotation.Membership;

import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "3")
public class FansMemership implements MembershipHandler,Commitable {
    @Override
    public Object process(Map<String, String> map) {

        return null;
    }

    @Override
    public boolean commit() {
        return false;
    }
}
