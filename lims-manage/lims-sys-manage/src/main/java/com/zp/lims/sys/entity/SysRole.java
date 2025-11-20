package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.lims.common.core.entity.BaseEntity;
import com.zp.lims.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Role
 *
 * @author zhengpanone
 * @since 2022-11-28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {
    /**
     * 角色ID
     */
    @TableId
    private String id;
    /**
     * 角色名称
     */
    private String name;

    private String code;

    private String description;

    private String dataScope;

    /**
     * 是否启用
     */
    @TableField("status")
    private StatusEnum status;
}
