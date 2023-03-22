package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Data
@TableName("sys_user_role")
public class SysUserRole {
    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
