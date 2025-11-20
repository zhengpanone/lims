package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 用户组表
@Data
@TableName("sys_user_group")
public class SysUserGroup {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;
}
