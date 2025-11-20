package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.lims.common.core.entity.BaseEntity;
import com.zp.lims.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 数据字典类别
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Accessors(chain = true)
@TableName("sys_lookup_classify")
public class SysLookupClassifyEntity extends BaseEntity {
    @TableId(value = "classify_id", type = IdType.ASSIGN_ID)
    private Long classifyId;

    private String classifyCode;

    private String parentCode;
    private String classifyName;
    private String classifyDesc;
    /**
     * 启用&禁用
     */
    private StatusEnum status;


}
