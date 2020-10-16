package com.snail.web.common.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    public String[] roleId() default {};
}

