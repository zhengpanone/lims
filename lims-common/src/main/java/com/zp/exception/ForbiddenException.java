package com.zp.exception;

/**
 * ForbiddenException 自定义权限异常
 *
 * @author zhengpanone
 * @since 2022-11-07
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
