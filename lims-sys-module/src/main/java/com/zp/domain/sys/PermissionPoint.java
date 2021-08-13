package com.zp.domain.sys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;

import lombok.Data;

/**
 * @Author: zhengpanone
 * @Description: 按钮资源
 * @Date:Created in 2021/08/07 17:20.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "sys_permission_point")
@Data
public class PermissionPoint extends BaseEntity implements Serializable {
    @Id
    private String id;
    /**
     * 权限代码
     */
    private String pointClass;
    private String pointIcon;
    private String pointStatus;
}
