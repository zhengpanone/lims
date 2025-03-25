package com.zp.aspect;

import com.zp.utils.ThreadContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Author : zhengpanone
 * Date : 2025/3/25 13:22
 * Version : v1.0.0
 * Description: 执行完任务后自动清理 MDC
 */
@Aspect
@Component
public class AsyncThreadContextAspect {

    @Around("@annotation(org.springframework.scheduling.annotation.Async)")
    public Object aroundAsyncMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } finally {
            // 确保异步任务完成后清理 MDC
            ThreadContextUtil.clearThreadContext();
        }
    }
}
