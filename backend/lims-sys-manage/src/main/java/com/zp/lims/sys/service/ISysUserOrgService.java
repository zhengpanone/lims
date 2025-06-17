package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserOrg;

import java.util.List;

/**
 * ISysUserOrgService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组织服务接口
 */
public interface ISysUserOrgService extends IService<SysUserOrg> {
    /**
     * 根据用户ID查询组织列表
     * @param userId
     * @return
     */
    List<SysUserOrg> getByUserId(Long userId);
    
    /**
     * 根据组织ID查询用户列表
     * @param orgId
     * @return
     */
    List<SysUserOrg> getByOrgId(Long orgId);
    
    /**
     * 为用户分配组织
     * @param userId
     * @param orgIds
     * @return
     */
    Boolean assignOrgsToUser(Long userId, List<Long> orgIds);
} 