package com.alpha.spring;

import com.alpha.spring.biz.ProductStore;

/**
 * Created by ssports on 2018/4/16.
 */
public interface IRegistry<T> {
    void register(T t);
    void notify(ProductStore.Product product);
}
