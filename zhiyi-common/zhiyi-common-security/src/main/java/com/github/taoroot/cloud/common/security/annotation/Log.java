package com.github.taoroot.cloud.common.security.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    public String value() default "";

    public int businessType() default 0;

    public int operatorType() default 0;

    public boolean isSaveRequestData() default true;
}
