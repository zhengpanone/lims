package com.zp.domain.sys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;

import java.io.Serializable;

/**
 * @Author: zhengpanone
 * @Description: 菜单资源
 * @Date:Created in 2021/08/07 17:20.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "sys_permission_menu")
@Data
public class PermissionMenu extends BaseEntity implements Serializable {
    @Id
    private String id;
    /**
     * 展示图标
     */
    private String menuIcon;
    /**
     * 排序号
     */
    private String menuOrder;
}
