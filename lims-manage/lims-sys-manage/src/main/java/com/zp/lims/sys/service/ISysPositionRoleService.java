package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysPositionRole;

import java.util.List;

/**
 * ISysPositionRoleService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位角色服务接口
 */
public interface ISysPositionRoleService extends IService<SysPositionRole> {
    /**
     * 根据职位ID查询角色列表
     * @param positionId
     * @return
     */
    List<SysPositionRole> getByPositionId(Long positionId);
    
    /**
     * 根据角色ID查询职位列表
     * @param roleId
     * @return
     */
    List<SysPositionRole> getByRoleId(Long roleId);
    
    /**
     * 为职位分配角色
     * @param positionId
     * @param roleIds
     * @return
     */
    Boolean assignRolesToPosition(Long positionId, List<Long> roleIds);
} 