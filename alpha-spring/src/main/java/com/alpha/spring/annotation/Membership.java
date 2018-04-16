package com.alpha.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by qiang on 2018/3/29.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Membership {
    String packageId() default "";
    String productId() default "";
}
