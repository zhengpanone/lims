package com.zp.lims.common.aspect;

import com.zp.lims.common.annotation.LimitRequest;
import com.zp.lims.common.exception.BusinessException;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * LimitRequesstAspect
 *
 * @author zhengpanone
 * @since 2022-11-25
 */
@Aspect
@Component
public class LimitRequestAspect {
    private static ConcurrentHashMap<String, ExpiringMap<String, Integer>> book = new ConcurrentHashMap<>();

    /**
     * 定义切点
     * 让所有有@LimitRequest注解的方法都执行切面方法
     *
     * @param limitRequest
     */
    @Pointcut("@annotation(limitRequest)")
    public void executeService(LimitRequest limitRequest) {
    }

    @Around(value = "executeService(limitRequest)", argNames = "pjp,limitRequest")
    public Object doAround(ProceedingJoinPoint pjp, LimitRequest limitRequest) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        // 获取Map对象，如果没有则返回默认值
        // 第一个参数是key,第二个参数是默认值
        ExpiringMap<String, Integer> uc = book.getOrDefault(request.getRequestURI(), ExpiringMap.builder().variableExpiration().build());
        Integer uCount = uc.getOrDefault(request.getRemoteAddr(), 0);
        if(uCount>=limitRequest.count()){// 超过次数,不执行目标方法
            throw new BusinessException("接口请求超过次数");
        }else if(uCount==0){ // 第一次请求时,设置有效时间
            uc.put(request.getRemoteAddr(),uCount+1, ExpirationPolicy.CREATED,limitRequest.time(), TimeUnit.MILLISECONDS);

        }else{ // 未超过次数,记录加一
            uc.put(request.getRemoteAddr(),uCount+1);
        }
        book.put(request.getRequestURI(),uc);
        Object result = pjp.proceed();
        return result;
    }
}
