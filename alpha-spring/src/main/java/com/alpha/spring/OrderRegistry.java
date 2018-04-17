package com.alpha.spring;

import com.alpha.spring.biz.MembershipHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
@Component
public class OrderRegistry implements IRegistry<MembershipHandler> {
    public final static String ID_DELIMITER = "#";
    private Map<String,MembershipHandler> registry = new HashMap<>();
    @Override
    public void register(MembershipHandler membershipHandler) {

    }

    @Override
    public void notify(String id) {

    }
}
