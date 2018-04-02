package com.alpha.java;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiang on 2018/3/28.
 */
@SpringBootApplication
public class JavaApplication implements ApplicationRunner {

    static Map<String,ClassLoader> map = new HashMap<>();
    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        AlphaClassLoader alphaClassLoader = new AlphaClassLoader("D:/");
        Class cls = alphaClassLoader.loadClass("com.alpha.java.Demo");

        Thread.currentThread().setContextClassLoader(alphaClassLoader);
        map.put("alpha",alphaClassLoader);
        //---------------------------------------------------------------------------------------------------------------------

//        Class t_cls = Thread.currentThread().getContextClassLoader().loadClass("com.alpha.java.Demo");

        Class t_cls = map.get("alpha").loadClass("com.alpha.java.Demo");
        System.out.println(t_cls.getClassLoader());
        Method method = t_cls.getMethod("say");
        Object res = method.invoke(t_cls.newInstance());
        System.out.println("res=" + res);

        System.out.println(cls.getClassLoader());
    }

}