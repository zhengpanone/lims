package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserGroupRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserGroupRole
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组角色表数据访问层
 */
public interface SysUserGroupRoleMapper extends BaseMapper<SysUserGroupRole> {
    /**
     * 根据用户组ID查询角色列表
     * @param userGroupId
     * @return
     */
    List<SysUserGroupRole> selectByUserGroupId(@Param("userGroupId") Long userGroupId);
    
    /**
     * 根据角色ID查询用户组列表
     * @param roleId
     * @return
     */
    List<SysUserGroupRole> selectByRoleId(@Param("roleId") Long roleId);
} 