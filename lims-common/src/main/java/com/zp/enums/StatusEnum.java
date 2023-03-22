package com.zp.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * StatusEnum 启用禁用枚举类
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  StatusEnum implements IEnum<String> {
    //启用
    ACTIVE("1","启用"),
    // 禁用
    DISABLED("0","禁用");
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getValue() {
        return code;
    }

    public String getName() {
        return name;
    }
}
