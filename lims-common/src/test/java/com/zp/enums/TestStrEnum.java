package com.zp.enums;

import com.zp.util.enums.NameValueEnum;

/**
 * TestStrEnum
 *
 * @author zhengpanone
 * @since 2022-02-22
 */
public enum TestStrEnum implements NameValueEnum<String> {
    T1("01", "String类型测试01"),
    T2("02", "String类型测试02");
    private final String value;
    private final String name;

    TestStrEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
