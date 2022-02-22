package com.zp.util.enums;

/**
 * ValueEnum
 * 最简单的枚举类，只含value的枚举
 *
 * @author zhengpanone
 * @since 2022-02-22
 */
public interface ValueEnum<T> {
    /**
     * 获取枚举值
     * @return 枚举值
     */
    T getValue();
}
