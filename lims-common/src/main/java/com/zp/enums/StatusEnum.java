package com.zp.enums;

import com.zp.util.enums.NameValueEnum;

/**
 * StatusEnum 启用状态
 *
 * @author zhengpanone
 * @since 2022-02-22
 */
public enum  StatusEnum implements NameValueEnum<Integer> {
    /**
     * 启用状态
     */
    ENABLE(1,"启用"),
    /**
     * 禁用状态
     */
    DISABLE(0,"禁用");
    private final Integer value;
    private final String name;

    StatusEnum(Integer value,String name){
        this.value=value;
        this.name=name;
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
