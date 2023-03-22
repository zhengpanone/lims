package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.entity.BaseEntity;
import com.zp.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Role
 *
 * @author zhengpanone
 * @since 2022-11-28
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity {
    /**
     * 角色ID
     */
    @TableId
    @TableField("id")
    private String id;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 说明
     */
    @TableField("remark")
    private String remark;
    /**
     * 角色所属系统ID
     */
    @TableField("system_id")
    private String systemId;
    /**
     * 是否启用
     */
    @TableField("status")
    private StatusEnum status;
}
