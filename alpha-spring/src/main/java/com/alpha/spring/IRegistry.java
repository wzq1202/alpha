package com.alpha.spring;

/**
 * Created by ssports on 2018/4/16.
 */
public interface IRegistry<T> {
    void register(T t);
    void notify(String id);
}
