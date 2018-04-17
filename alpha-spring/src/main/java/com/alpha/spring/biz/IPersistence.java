package com.alpha.spring.biz;

import java.util.Map;

/**
 * Created by ssports on 2018/4/16.
 */
public interface IPersistence {
    boolean commit(Map<String,String> map);
}
