package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 用户-用户组关联表
@Data
@TableName("sys_user_user_group")
public class SysUserUserGroup {
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @TableId(value = "user_group_id", type = IdType.INPUT)
    private Long userGroupId;
}