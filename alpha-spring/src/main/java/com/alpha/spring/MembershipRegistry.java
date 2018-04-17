package com.alpha.spring;

import com.alpha.spring.annotation.Membership;
import com.alpha.spring.biz.AbstractMembershipHandler;
import com.alpha.spring.biz.MembershipHandler;
import com.alpha.spring.biz.ProductStore;
import com.alpha.spring.biz.RedisCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by ssports on 2018/4/16.
 */
@Component
public class MembershipRegistry implements IRegistry<AbstractMembershipHandler> {
    public final static String ID_DELIMITER = "#";
    private Map<String,AbstractMembershipHandler> registry = new HashMap<>();
    @Autowired
    private RedisCommand redisCommand;
    @Override
    public void register(AbstractMembershipHandler membershipHandler) {
        Membership ann = membershipHandler.getClass().getAnnotation(Membership.class);
        String pckid = ann.packageId();
       /* String pid = ann.productId();
        StringJoiner joiner = new StringJoiner(ID_DELIMITER);
        joiner.add(pckid);
        if (!StringUtils.isEmpty(pid)) {
            joiner.add(pid);
        }*/

        if (registry.containsKey(pckid)) {
            throw new RuntimeException("Duplicate pckid. The pckid is " + pckid);
        }
        registry.put(pckid,membershipHandler);
    }

    @Override
    public void notify(String id) {
        AbstractMembershipHandler membershipHandler = registry.get(id);
        membershipHandler.setAutoCommit(false);
        membershipHandler.process(null);
    }

    public void notify(ProductStore.Product product) {
        String pckId = product.getPackageId();
        this.notify(pckId);
    }

    public void notify(List<ProductStore.Product> list) {
        Map<String,String> m_map = new HashMap<>();
        for (ProductStore.Product p : list) {
            AbstractMembershipHandler membershipHandler = registry.get(p.getPackageId());
            membershipHandler.setAutoCommit(false);
            Map<String,String> map = membershipHandler.process(null);
            map.forEach((k,v) -> m_map.merge(k,v,(s, s2) -> s2));
        }
        redisCommand.hmset(m_map);

    }
}
