package com.alpha.spring.biz;

import com.alpha.spring.annotation.Executor;

/**
 * Created by qiang on 2018/4/1.
 */
@Executor(name = "DDD",mypck = "7772")
@SuppressWarnings("unchecked")
public class MyDemo {
    private String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
