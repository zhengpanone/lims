package com.zp.aspect;

import com.alibaba.fastjson.JSON;
import com.zp.annotation.SysLog;
import com.zp.module.SysLogEntity;
import com.zp.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysLogAspect
 *
 * @author zhengpanone
 * @since 2021-11-17
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    @Resource
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.zp.annotation.SysLog)")
    public void logPointCut() {
        log.info("SysLog pointCut");
    }

    @Before("logPointCut()")
    public void doBefore() {
        log.info("---------------syslog before------------");
    }

    @After("logPointCut()")
    public void doAfter() {
        log.info("-------------syslog do After------------------");
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        SysLogEntity sysLogEntity = new SysLogEntity();
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取方法签名
        Method method = signature.getMethod();
        SysLog annotation = method.getAnnotation(SysLog.class);
        sysLogEntity.setOperation(annotation.value());
        String methodName = signature.getName();
        String clazzName = proceedingJoinPoint.getTarget().getClass().getName();
        sysLogEntity.setMethod(clazzName + "." + methodName + "()");
        List<Object> collect = Arrays.stream(proceedingJoinPoint.getArgs()).filter(param -> !(param instanceof ServletRequest)
                && !(param instanceof ServletResponse)
                && !(param instanceof HttpSession)
                && !(param instanceof BindResult))
                .collect(Collectors.toList());
        String params = JSON.toJSONString(collect);
        sysLogEntity.setParams(params);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}
