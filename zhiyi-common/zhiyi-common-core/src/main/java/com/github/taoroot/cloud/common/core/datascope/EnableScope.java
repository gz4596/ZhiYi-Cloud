package com.github.taoroot.cloud.common.core.datascope;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableScope {
     String scopeOwnName() default  "user_id";
}
