package com.zp.domain.sys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionPoint extends BaseEntity  {
    @Id
    private String id;
    /**
     * 权限代码
     */
    private String pointClass;
    private String pointIcon;
    private String pointStatus;
}
