package com.alpha.spring.biz;

import com.alpha.spring.annotation.Membership;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Membership(packageId = "3")
public class FansMemership extends AbstractMembershipHandler{

    @Override
    public Map<String,String> process(ProductStore.Product product) {
        System.out.println("do FansMemership  ");
        return new HashMap(){{this.put("t_63","2019-04-20");}};
    }
}
