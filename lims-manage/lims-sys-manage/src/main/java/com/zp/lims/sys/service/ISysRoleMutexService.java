package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysRoleMutex;

import java.util.List;

/**
 * ISysRoleMutexService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 角色互斥服务接口
 */
public interface ISysRoleMutexService extends IService<SysRoleMutex> {
    /**
     * 根据角色ID查询互斥角色列表
     * @param roleId
     * @return
     */
    List<SysRoleMutex> getByRoleId(Long roleId);
    
    /**
     * 检查两个角色是否互斥
     * @param roleId1
     * @param roleId2
     * @return
     */
    Boolean isMutex(Long roleId1, Long roleId2);
} 