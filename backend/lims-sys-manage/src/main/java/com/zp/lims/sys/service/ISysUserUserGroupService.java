package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserUserGroup;

import java.util.List;

/**
 * ISysUserUserGroupService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户用户组服务接口
 */
public interface ISysUserUserGroupService extends IService<SysUserUserGroup> {
    /**
     * 根据用户ID查询用户组列表
     * @param userId
     * @return
     */
    List<SysUserUserGroup> getByUserId(Long userId);
    
    /**
     * 根据用户组ID查询用户列表
     * @param userGroupId
     * @return
     */
    List<SysUserUserGroup> getByUserGroupId(Long userGroupId);
    
    /**
     * 为用户分配用户组
     * @param userId
     * @param userGroupIds
     * @return
     */
    Boolean assignUserGroupsToUser(Long userId, List<Long> userGroupIds);
} 