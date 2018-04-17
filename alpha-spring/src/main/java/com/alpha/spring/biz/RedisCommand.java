package com.alpha.spring.biz;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ssports on 2018/4/17.
 */
@Component
public class RedisCommand {
    public boolean hmset(Map<String,String> map){
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("do RedisCommand#hmset " + jsonObject.toString());
        return true;
    }
}
