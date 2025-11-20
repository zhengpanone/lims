package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysPermission;

import java.util.List;

/**
 * ISysPermissionService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 权限服务接口
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 根据权限代码查询权限
     * @param code
     * @return
     */
    SysPermission getByCode(String code);
    
    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<SysPermission> getByRoleId(Long roleId);
    
    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<SysPermission> getByUserId(Long userId);
    
    /**
     * 获取权限树结构
     * @return
     */
    List<SysPermission> getPermissionTree();
} 