package com.zp.lims.common.annotation;

import java.lang.annotation.*;

/**
 * LimitRequest
 * @description: 限制ip访问次数注解
 * @author zhengpanone
 * @since 2022-11-25
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {
    long time() default 6000; // 限制时间 单位:毫秒
    int count() default 5; // 允许请求的次数
}
