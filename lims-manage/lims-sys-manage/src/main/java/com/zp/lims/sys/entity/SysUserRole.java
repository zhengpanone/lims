package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User
 *
 * @author zhengpanone
 * @since 2022-11-28
 * 用户-角色关联表
 */
@Data
@TableName("sys_user_role")
public class SysUserRole {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private String roleId;


}
