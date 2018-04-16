package com.alpha.spring;

import com.alpha.spring.annotation.Membership;
import com.alpha.spring.biz.MembershipHandler;
import com.alpha.spring.biz.ProductStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by ssports on 2018/4/16.
 */
@Component
public class MembershipRegistry implements IRegistry<MembershipHandler> {
    public final static String ID_DELIMITER = "#";
    private Map<String,MembershipHandler> registry = new HashMap<>();
    @Override
    public void register(MembershipHandler membershipHandler) {
        Membership ann = membershipHandler.getClass().getAnnotation(Membership.class);
        String pckid = ann.packageId();
        String pid = ann.productId();
        StringJoiner joiner = new StringJoiner(ID_DELIMITER);
        joiner.add(pckid);
        if (!StringUtils.isEmpty(pid)) {
            joiner.add(pid);
        }

        if (registry.containsKey(pckid)) {
            throw new RuntimeException("Duplicate pckid. The pckid is " + pckid);
        }
        registry.put(joiner.toString(),membershipHandler);
    }




    @Override
    public void notify(String id) {
        MembershipHandler membershipHandler = registry.get(id);
        membershipHandler.process(null);
    }

    public void notify(ProductStore.Product product) {
        String pckId = product.getPackageId();
        this.notify(pckId);
    }

}
