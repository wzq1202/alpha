package com.alpha.os.cpu;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 锁竞争对服务器的影响
 * Created by qiang on 2018/3/28.
 */
@Component
public class Sync4cpu{
    private volatile boolean isRun = true;
    private final static Object lock = new Object();
    private Map<String,String> map = new HashMap<>();

    public void compete(){

        for (int i = 0; i < 16; i++) {
            Thread t1 = new Thread("t" + i){
                @Override
                public void run() {
                    doAction(map);
                }
            };
            t1.start();
        }

    }

    void doAction(Map<String,String> map){
        while (isRun) {
            synchronized (lock) {
                String name = Thread.currentThread().getName();
                map.remove(name);
                map.put(name,"A");
            }
//            System.out.println(map);
        }
    }

    public Map<String, String> getMap() {
        return map;
    }
}
