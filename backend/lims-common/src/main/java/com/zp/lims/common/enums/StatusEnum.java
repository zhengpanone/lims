package com.zp.lims.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * StatusEnum 启用禁用枚举类
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  StatusEnum implements IEnum<Integer> {
    //启用
    ACTIVE(1,"启用"),
    // 禁用
    DISABLED(0,"禁用");
    /**
     * 编码
     */
    @EnumValue //标记数据库存的值是code
    @JsonValue //标记响应json值
    private final Integer code;
    /**
     * 名称
     */
    private final  String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    public String getName() {
        return name;
    }
}
