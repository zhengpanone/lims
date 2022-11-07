package com.zp.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ArgumentResponseEnum
 *
 * @author zhengpanone
 * @since 2022-11-07
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum {
    VALID_ERROR(5000, "参数错误");
    /**
     * 操作代码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;
}
