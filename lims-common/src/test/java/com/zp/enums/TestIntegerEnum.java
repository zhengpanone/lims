package com.zp.enums;

import com.zp.util.enums.NameValueEnum;

/**
 * TestIntegerEnum
 *
 * @author zhengpanone
 * @since 2022-02-22
 */
public enum TestIntegerEnum implements NameValueEnum<Integer> {
    T1(1,"Integer测试01"),
    T2(2,"Integer测试02")
    ;
    private final Integer value;
    private final String name;

    TestIntegerEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
