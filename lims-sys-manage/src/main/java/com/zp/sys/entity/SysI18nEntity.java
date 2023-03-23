package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zp.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * i18n 国际化
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysI18nEntity extends BaseEntity {

    @TableId(value = "i18n_id", type = IdType.ASSIGN_ID)
    private Long i18nId;
    /**
     * 模块类型
     * 1.菜单
     * 2.数据字典
     * 3.其它:系统界面上的一些元素
     */
    private Integer moduleType;
    /**
     * 语言类型
     */
    private String langType;

    private String key;

    private String value;

}