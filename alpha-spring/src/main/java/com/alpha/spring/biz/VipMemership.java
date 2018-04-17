package com.alpha.spring.biz;

import com.alpha.spring.annotation.Membership;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "999",productId = "111")
public class VipMemership extends AbstractMembershipHandler{

    @Override
    public Map<String,String> process(ProductStore.Product product) {
        System.out.println("do VipMemership  ");
        return new HashMap(){{this.put("t_63","2019-04-20");}};
    }
}
