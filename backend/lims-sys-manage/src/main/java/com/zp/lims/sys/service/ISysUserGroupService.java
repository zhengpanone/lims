package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserGroup;

/**
 * ISysUserGroupService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组服务接口
 */
public interface ISysUserGroupService extends IService<SysUserGroup> {
    /**
     * 根据用户组名称查询用户组
     * @param name
     * @return
     */
    SysUserGroup getByName(String name);
} 