package com.snail.web.common.anno;

import java.lang.annotation.*;

/**
 * Created by Mei on 2019/8/19.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OutAuth {
}
