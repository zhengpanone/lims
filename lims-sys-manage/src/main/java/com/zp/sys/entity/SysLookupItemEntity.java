package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.base.entity.BaseEntity;
import com.zp.enums.StatusEnum;
import lombok.Data;

/**
 * 数据字典项
 */
@Data
@TableName("sys_lookup_item")
public class SysLookupItemEntity extends BaseEntity {
    @TableId(value = "item_id", type = IdType.ASSIGN_ID)
    private Long itemId;

    private StatusEnum status;
}
