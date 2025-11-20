package com.zp.lims.sys.handler;

import com.zp.lims.common.core.response.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 25. 全局异常处理器
@ControllerAdvice
public class SysGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(SysGlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMessage.append(fieldName).append(": ").append(message).append("; ");
        });
        return R.error(400, errorMessage.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<Void> handleGenericException(Exception ex) {
        logger.error("Unhandled exception: ", ex);
        return R.error("系统异常，请联系管理员");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public R<Void> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return R.error(401, "用户名或密码错误");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public R<Void> handleBadCredentialsException(BadCredentialsException ex) {
        return R.error(401, "用户名或密码错误");
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseBody
    public R<Void> handleDisabledException(DisabledException ex) {
        return R.error(403, "账户已被禁用");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public R<Void> handleAccessDeniedException(AccessDeniedException ex) {
        return R.error(403, "权限不足");
    }
}