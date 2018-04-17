package com.alpha.spring.biz;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by ssports on 2018/4/17.
 */
public abstract class AbstractMembershipHandler implements MembershipHandler,IPersistence {

    protected boolean autoCommit = true;
    @Autowired
    private RedisCommand redisCommand;

    @Override
    public Map<String, String> process(Map<String, String> map) {
        if(autoCommit) {
            this.commit(map);
        }
        return null;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @Override
    public boolean commit(Map<String, String> map) {
        return redisCommand.hmset(map);
    }
}
