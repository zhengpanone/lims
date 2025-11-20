package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zp.lims.common.core.entity.BaseEntity;

import com.zp.lims.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    private StatusEnum status;

}
