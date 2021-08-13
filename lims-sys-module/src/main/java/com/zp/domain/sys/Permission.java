package com.zp.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;

import java.io.Serializable;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 17:19.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "sys_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity implements Serializable {
    @Id
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型: 1 为菜单; 2为功能; 3为API
     */
    private Integer type;
    /**
     * 权限标识
     */
    private String code;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 父ID
     * 菜单 -> 按钮
     * 菜单 -> api权限
     */
    private String pid;
    /**
     * 是否可见
     */
    private Integer enVisible;

    public Permission(String name, Integer type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }
}
