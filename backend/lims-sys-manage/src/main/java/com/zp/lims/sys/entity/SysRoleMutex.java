package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 角色互斥表
@Data
@TableName("sys_role_mutex")
public class SysRoleMutex {
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    @TableId(value = "mutex_role_id", type = IdType.INPUT)
    private Long mutexRoleId;
}