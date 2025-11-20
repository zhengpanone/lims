package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.lims.common.core.entity.BaseEntity;
import com.zp.lims.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 数据字典项
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_lookup_item")
public class SysLookupItemEntity extends BaseEntity {
    @TableId(value = "item_id", type = IdType.ASSIGN_ID)
    private Long itemId;

    private StatusEnum status;
}
