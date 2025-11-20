package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserGroupRole;

import java.util.List;

/**
 * ISysUserGroupRoleService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组角色服务接口
 */
public interface ISysUserGroupRoleService extends IService<SysUserGroupRole> {
    /**
     * 根据用户组ID查询角色列表
     * @param userGroupId
     * @return
     */
    List<SysUserGroupRole> getByUserGroupId(Long userGroupId);
    
    /**
     * 根据角色ID查询用户组列表
     * @param roleId
     * @return
     */
    List<SysUserGroupRole> getByRoleId(Long roleId);
    
    /**
     * 为用户组分配角色
     * @param userGroupId
     * @param roleIds
     * @return
     */
    Boolean assignRolesToUserGroup(Long userGroupId, List<Long> roleIds);
} 