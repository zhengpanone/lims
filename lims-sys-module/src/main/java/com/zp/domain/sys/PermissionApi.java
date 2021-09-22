package com.zp.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengpanone
 * @Description: API资源
 * @Date:Created in 2021/08/07 17:20.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "sys_permission_api")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionApi extends BaseEntity  {
    @Id
    private String id;
    /**
     * URL
     */
    private String apiUrl;
    /**
     * 请求类型
     */
    private String apiMethod;
    /**
     * 权限等级：1为通用接口, 2为需要校验权限接口
     */
    private String apiLevel;
}
