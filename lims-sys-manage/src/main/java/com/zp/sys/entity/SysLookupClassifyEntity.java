package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.base.entity.BaseEntity;
import com.zp.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据字典类别
 */
@Data
@Builder
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
