package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 用户组-角色关联表
@Data
@TableName("sys_user_group_role")
public class SysUserGroupRole {
    @TableId(value = "user_group_id", type = IdType.INPUT)
    private Long userGroupId;

    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;
}