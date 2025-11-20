package com.zp.lims.common.core.response.enums;

import lombok.AllArgsConstructor;



/**
 * @Author: zhengpanone
 * @Description: 响应码枚举
 * @Date:Created in 2021/08/06 21:15.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */

@AllArgsConstructor
public enum CommonResponseEnum implements IResult {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    VALIDATE_FAILED(2002, "参数校验失败"),
    COMMON_FAILED(2003, "接口调用失败"),
    FORBIDDEN(2004, "没有权限访问资源"),
    FAILED(99999, "系统繁忙，请稍后再试.");

    /**
     * 操作代码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
