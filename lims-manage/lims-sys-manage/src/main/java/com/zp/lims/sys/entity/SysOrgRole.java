package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 组织-角色关联表
@Data
@TableName("sys_org_role")
public class SysOrgRole {
    @TableId(value = "org_id", type = IdType.INPUT)
    private Long orgId;

    @TableField(value = "role_id")
    private Long roleId;
}