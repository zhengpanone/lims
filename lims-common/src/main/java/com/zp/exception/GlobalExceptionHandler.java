package com.zp.exception;

import com.zp.response.R;
import com.zp.response.enums.ArgumentResponseEnum;
import com.zp.response.enums.CommonResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler 全局异常处理，处理自定义的异常
 * @author zhengpanone
 * @since 2022-11-07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public R<?> handle(Exception e) {
        return R.failed(e.getMessage());
    }

    /**
     * 捕获 {@code BusinessException} 异常
     */
    @ExceptionHandler({BusinessException.class})
    public R<?> handleBusinessException(BusinessException ex) {
        return R.failed(ex.getMessage());
    }

    /**
     * 捕获 {@code ForbiddenException} 异常
     */
    @ExceptionHandler({ForbiddenException.class})
    public R<?> handleForbiddenException(ForbiddenException ex) {
        return R.failed(CommonResponseEnum.FORBIDDEN);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return R.failed(20000, "ConstraintViolationException，输入数据异常" + message);
    }


    /**
     * 参数绑定异常
     * 处理Get请求,使用@Valid 验证路径中请求实体校验失败后抛出的异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    public R<?> handleBindException(BindException e) {
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     * 处理Post请求,参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R<?> handleValidException(MethodArgumentNotValidException e) {
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private R<?> wrapperBindingResult(BindingResult bindingResult) {
        StringBuffer msg = new StringBuffer();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(",");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return R.failed(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(1));
    }

}
