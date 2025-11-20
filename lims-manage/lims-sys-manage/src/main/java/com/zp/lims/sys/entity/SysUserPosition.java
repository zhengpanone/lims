package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 用户-职位关联表
@Data
@TableName("sys_user_position")
public class SysUserPosition {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @TableField(value = "position_id")
    private Long positionId;

    @TableField(value = "org_id")
    private Long orgId;
}
