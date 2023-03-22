package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * UserRole
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Data
@TableName("sys_user_role")
public class UserRole {
    private String userId;
    private String roleId;
}
