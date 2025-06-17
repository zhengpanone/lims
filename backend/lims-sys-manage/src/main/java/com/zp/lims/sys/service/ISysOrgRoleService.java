package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysOrgRole;

import java.util.List;

/**
 * ISysOrgRoleService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织角色服务接口
 */
public interface ISysOrgRoleService extends IService<SysOrgRole> {
    /**
     * 根据组织ID查询角色列表
     * @param orgId
     * @return
     */
    List<SysOrgRole> getByOrgId(Long orgId);
    
    /**
     * 根据角色ID查询组织列表
     * @param roleId
     * @return
     */
    List<SysOrgRole> getByRoleId(Long roleId);
    
    /**
     * 为组织分配角色
     * @param orgId
     * @param roleIds
     * @return
     */
    Boolean assignRolesToOrg(Long orgId, List<Long> roleIds);
} 