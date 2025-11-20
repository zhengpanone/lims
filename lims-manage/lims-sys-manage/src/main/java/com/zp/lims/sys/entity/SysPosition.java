package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 职位表
@Data
@TableName("sys_position")
public class SysPosition {

    @TableId(type = IdType.AUTO)
    private String id;

    private String name;

    private String code;

    private String description;
}