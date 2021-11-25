package com.zp.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author zhengpanone
 * @since 2021-11-17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
