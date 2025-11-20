package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 职位-角色关联表
@Data
@TableName("sys_position_role")
public class SysPositionRole {
    @TableId(value = "position_id", type = IdType.INPUT)
    private Long positionId;

    @TableField(value = "role_id")
    private Long roleId;
}