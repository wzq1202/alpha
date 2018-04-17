package com.alpha.spring.biz;

import com.alpha.spring.annotation.Membership;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "1")
public class DiamondMemership extends AbstractMembershipHandler{
    @Override
    public Map<String,String> process(Map<String, String> map) {
        System.out.println("do DiamondMemership  ");
        return new HashMap(){{this.put("diamond","2019-05-31");}};
    }
}
