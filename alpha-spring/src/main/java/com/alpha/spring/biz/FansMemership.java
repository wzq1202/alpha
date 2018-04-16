package com.alpha.spring.biz;

import com.alpha.spring.annotation.Membership;

import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "3")
public class FansMemership implements MembershipHandler,IPersistence {
    @Override
    public Object process(Map<String, String> map) {
        System.out.println("do FansMemership  ");
        return null;
    }


    @Override
    public boolean persistence() {
        return false;
    }
}
