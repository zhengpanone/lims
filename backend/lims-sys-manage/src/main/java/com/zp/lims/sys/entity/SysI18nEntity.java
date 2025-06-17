package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.lims.common.core.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * i18n 国际化
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@SuperBuilder
@TableName("sys_i18n")
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class SysI18nEntity extends BaseEntity {

    @TableId(value = "i18n_id", type = IdType.ASSIGN_ID)
    private Long i18nId;
    /**
     * 模块类型
     * 1.菜单
     * 2.数据字典
     * 3.其它:系统界面上的一些元素
     */
    @ApiModelProperty(value = "模块类型")
    private Integer moduleType;
    /**
     * 语言类型
     */
    @ApiModelProperty(value = "语言类型")
    private String langType;
    @ApiModelProperty(value = "国际化key")
    private String key;
    @ApiModelProperty(value = "国际化value")
    private String value;

}
