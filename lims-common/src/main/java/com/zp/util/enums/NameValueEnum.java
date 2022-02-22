package com.zp.util.enums;

/**
 * NameValueEnum
 * 带有枚举值以及枚举名称的枚举接口
 * @author zhengpanone
 * @since 2022-02-22
 */
public interface NameValueEnum<T> extends ValueEnum<T> {
    /**
     * 获取枚举名称
     * @return 枚举名
     */
    String getName();
}
