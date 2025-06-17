package com.zp.lims.common.exception;

/**
 * BusinessException 自定义业务异常
 * @author zhengpanone
 * @since 2022-11-07
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
