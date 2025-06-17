package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 角色-权限关联表
@Data
@TableName("sys_role_permission")
public class SysRolePermission {
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    @TableId(value = "permission_id", type = IdType.INPUT)
    private Long permissionId;
}