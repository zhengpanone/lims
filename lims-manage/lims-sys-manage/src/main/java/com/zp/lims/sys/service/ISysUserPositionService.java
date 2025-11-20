package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserPosition;

import java.util.List;

/**
 * ISysUserPositionService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户职位服务接口
 */
public interface ISysUserPositionService extends IService<SysUserPosition> {
    /**
     * 根据用户ID查询职位列表
     * @param userId
     * @return
     */
    List<SysUserPosition> getByUserId(Long userId);
    
    /**
     * 根据职位ID查询用户列表
     * @param positionId
     * @return
     */
    List<SysUserPosition> getByPositionId(Long positionId);
    
    /**
     * 为用户分配职位
     * @param userId
     * @param positionIds
     * @return
     */
    Boolean assignPositionsToUser(Long userId, List<Long> positionIds);
} 