package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 用户-组织关联表
@Data
@TableName("sys_user_org")
public class SysUserOrg {
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @TableId(value = "org_id", type = IdType.INPUT)
    private Long orgId;
}